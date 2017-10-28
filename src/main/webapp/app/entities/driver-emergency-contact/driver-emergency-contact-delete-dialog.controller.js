(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DriverEmergencyContactDeleteController',DriverEmergencyContactDeleteController);

    DriverEmergencyContactDeleteController.$inject = ['$uibModalInstance', 'entity', 'DriverEmergencyContact'];

    function DriverEmergencyContactDeleteController($uibModalInstance, entity, DriverEmergencyContact) {
        var vm = this;

        vm.driverEmergencyContact = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DriverEmergencyContact.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
