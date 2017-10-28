(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DriverMessageDeleteController',DriverMessageDeleteController);

    DriverMessageDeleteController.$inject = ['$uibModalInstance', 'entity', 'DriverMessage'];

    function DriverMessageDeleteController($uibModalInstance, entity, DriverMessage) {
        var vm = this;

        vm.driverMessage = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DriverMessage.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
