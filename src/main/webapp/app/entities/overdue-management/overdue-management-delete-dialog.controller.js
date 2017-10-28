(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OverdueManagementDeleteController',OverdueManagementDeleteController);

    OverdueManagementDeleteController.$inject = ['$uibModalInstance', 'entity', 'OverdueManagement'];

    function OverdueManagementDeleteController($uibModalInstance, entity, OverdueManagement) {
        var vm = this;

        vm.overdueManagement = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            OverdueManagement.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
