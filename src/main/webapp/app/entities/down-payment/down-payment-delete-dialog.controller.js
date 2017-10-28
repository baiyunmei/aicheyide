(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DownPaymentDeleteController',DownPaymentDeleteController);

    DownPaymentDeleteController.$inject = ['$uibModalInstance', 'entity', 'DownPayment'];

    function DownPaymentDeleteController($uibModalInstance, entity, DownPayment) {
        var vm = this;

        vm.downPayment = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DownPayment.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
