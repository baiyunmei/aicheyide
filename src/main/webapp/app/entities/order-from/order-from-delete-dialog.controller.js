(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OrderFromDeleteController',OrderFromDeleteController);

    OrderFromDeleteController.$inject = ['$uibModalInstance', 'entity', 'OrderFrom'];

    function OrderFromDeleteController($uibModalInstance, entity, OrderFrom) {
        var vm = this;

        vm.orderFrom = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            OrderFrom.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
