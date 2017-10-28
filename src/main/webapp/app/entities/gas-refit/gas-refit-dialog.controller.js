(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('GasRefitDialogController', GasRefitDialogController);

    GasRefitDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'GasRefit'];

    function GasRefitDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, GasRefit) {
        var vm = this;

        vm.gasRefit = entity;
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
            if (vm.gasRefit.id !== null) {
                GasRefit.update(vm.gasRefit, onSaveSuccess, onSaveError);
            } else {
                GasRefit.save(vm.gasRefit, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:gasRefitUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
