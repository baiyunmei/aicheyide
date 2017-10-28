(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ReceiveVehicleDeleteController',ReceiveVehicleDeleteController);

    ReceiveVehicleDeleteController.$inject = ['$uibModalInstance', 'entity', 'ReceiveVehicle'];

    function ReceiveVehicleDeleteController($uibModalInstance, entity, ReceiveVehicle) {
        var vm = this;

        vm.receiveVehicle = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ReceiveVehicle.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
