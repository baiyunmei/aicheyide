(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DepositContractDialogController', DepositContractDialogController);

    DepositContractDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DepositContract'];

    function DepositContractDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DepositContract) {
        var vm = this;

        vm.depositContract = entity;
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
            if (vm.depositContract.id !== null) {
                DepositContract.update(vm.depositContract, onSaveSuccess, onSaveError);
            } else {
                DepositContract.save(vm.depositContract, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:depositContractUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
