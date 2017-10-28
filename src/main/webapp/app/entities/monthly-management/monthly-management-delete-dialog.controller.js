(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('MonthlyManagementDeleteController',MonthlyManagementDeleteController);

    MonthlyManagementDeleteController.$inject = ['$uibModalInstance', 'entity', 'MonthlyManagement'];

    function MonthlyManagementDeleteController($uibModalInstance, entity, MonthlyManagement) {
        var vm = this;

        vm.monthlyManagement = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            MonthlyManagement.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
