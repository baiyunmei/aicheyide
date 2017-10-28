(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('BuyVehicleRecordDeleteController',BuyVehicleRecordDeleteController);

    BuyVehicleRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'BuyVehicleRecord'];

    function BuyVehicleRecordDeleteController($uibModalInstance, entity, BuyVehicleRecord) {
        var vm = this;

        vm.buyVehicleRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            BuyVehicleRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
