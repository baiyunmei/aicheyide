(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RetreatDepositDialogController', RetreatDepositDialogController);

    RetreatDepositDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RetreatDeposit'];

    function RetreatDepositDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, RetreatDeposit) {
        var vm = this;

        vm.retreatDeposit = entity;
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
            if (vm.retreatDeposit.id !== null) {
                RetreatDeposit.update(vm.retreatDeposit, onSaveSuccess, onSaveError);
            } else {
                RetreatDeposit.save(vm.retreatDeposit, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:retreatDepositUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
