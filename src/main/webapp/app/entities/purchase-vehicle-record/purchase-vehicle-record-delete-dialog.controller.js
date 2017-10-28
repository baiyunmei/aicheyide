(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PurchaseVehicleRecordDeleteController',PurchaseVehicleRecordDeleteController);

    PurchaseVehicleRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'PurchaseVehicleRecord'];

    function PurchaseVehicleRecordDeleteController($uibModalInstance, entity, PurchaseVehicleRecord) {
        var vm = this;

        vm.purchaseVehicleRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PurchaseVehicleRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
