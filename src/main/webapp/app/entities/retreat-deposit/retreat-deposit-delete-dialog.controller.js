(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RetreatDepositDeleteController',RetreatDepositDeleteController);

    RetreatDepositDeleteController.$inject = ['$uibModalInstance', 'entity', 'RetreatDeposit'];

    function RetreatDepositDeleteController($uibModalInstance, entity, RetreatDeposit) {
        var vm = this;

        vm.retreatDeposit = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RetreatDeposit.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
