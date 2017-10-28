(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PurchaseTaxDeleteController',PurchaseTaxDeleteController);

    PurchaseTaxDeleteController.$inject = ['$uibModalInstance', 'entity', 'PurchaseTax'];

    function PurchaseTaxDeleteController($uibModalInstance, entity, PurchaseTax) {
        var vm = this;

        vm.purchaseTax = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PurchaseTax.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
