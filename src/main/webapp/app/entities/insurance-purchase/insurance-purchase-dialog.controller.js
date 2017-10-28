(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('InsurancePurchaseDialogController', InsurancePurchaseDialogController);

    InsurancePurchaseDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'InsurancePurchase'];

    function InsurancePurchaseDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, InsurancePurchase) {
        var vm = this;

        vm.insurancePurchase = entity;
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
            if (vm.insurancePurchase.id !== null) {
                InsurancePurchase.update(vm.insurancePurchase, onSaveSuccess, onSaveError);
            } else {
                InsurancePurchase.save(vm.insurancePurchase, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:insurancePurchaseUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
