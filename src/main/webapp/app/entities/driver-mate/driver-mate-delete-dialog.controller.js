(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DriverMateDeleteController',DriverMateDeleteController);

    DriverMateDeleteController.$inject = ['$uibModalInstance', 'entity', 'DriverMate'];

    function DriverMateDeleteController($uibModalInstance, entity, DriverMate) {
        var vm = this;

        vm.driverMate = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DriverMate.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
