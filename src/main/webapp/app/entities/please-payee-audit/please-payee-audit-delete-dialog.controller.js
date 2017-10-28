(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PleasePayeeAuditDeleteController',PleasePayeeAuditDeleteController);

    PleasePayeeAuditDeleteController.$inject = ['$uibModalInstance', 'entity', 'PleasePayeeAudit'];

    function PleasePayeeAuditDeleteController($uibModalInstance, entity, PleasePayeeAudit) {
        var vm = this;

        vm.pleasePayeeAudit = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PleasePayeeAudit.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
