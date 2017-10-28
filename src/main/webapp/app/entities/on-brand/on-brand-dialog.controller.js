(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OnBrandDialogController', OnBrandDialogController);

    OnBrandDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'OnBrand'];

    function OnBrandDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, OnBrand) {
        var vm = this;

        vm.onBrand = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.onBrand.id !== null) {
                OnBrand.update(vm.onBrand, onSaveSuccess, onSaveError);
            } else {
                OnBrand.save(vm.onBrand, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:onBrandUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
