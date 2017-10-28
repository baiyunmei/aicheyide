(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ClaimSettlementAuditDeleteController',ClaimSettlementAuditDeleteController);

    ClaimSettlementAuditDeleteController.$inject = ['$uibModalInstance', 'entity', 'ClaimSettlementAudit'];

    function ClaimSettlementAuditDeleteController($uibModalInstance, entity, ClaimSettlementAudit) {
        var vm = this;

        vm.claimSettlementAudit = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ClaimSettlementAudit.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
