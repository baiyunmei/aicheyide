(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RentVehicleRecordDeleteController',RentVehicleRecordDeleteController);

    RentVehicleRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'RentVehicleRecord'];

    function RentVehicleRecordDeleteController($uibModalInstance, entity, RentVehicleRecord) {
        var vm = this;

        vm.rentVehicleRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RentVehicleRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
