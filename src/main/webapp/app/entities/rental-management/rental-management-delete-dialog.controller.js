(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RentalManagementDeleteController',RentalManagementDeleteController);

    RentalManagementDeleteController.$inject = ['$uibModalInstance', 'entity', 'RentalManagement'];

    function RentalManagementDeleteController($uibModalInstance, entity, RentalManagement) {
        var vm = this;

        vm.rentalManagement = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RentalManagement.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
