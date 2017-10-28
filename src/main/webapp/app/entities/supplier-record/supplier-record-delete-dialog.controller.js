(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('SupplierRecordDeleteController',SupplierRecordDeleteController);

    SupplierRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'SupplierRecord'];

    function SupplierRecordDeleteController($uibModalInstance, entity, SupplierRecord) {
        var vm = this;

        vm.supplierRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            SupplierRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
