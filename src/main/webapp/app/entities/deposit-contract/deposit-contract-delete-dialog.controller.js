(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DepositContractDeleteController',DepositContractDeleteController);

    DepositContractDeleteController.$inject = ['$uibModalInstance', 'entity', 'DepositContract'];

    function DepositContractDeleteController($uibModalInstance, entity, DepositContract) {
        var vm = this;

        vm.depositContract = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DepositContract.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
