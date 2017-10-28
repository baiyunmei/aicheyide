(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('InsurancePurchaseDeleteController',InsurancePurchaseDeleteController);

    InsurancePurchaseDeleteController.$inject = ['$uibModalInstance', 'entity', 'InsurancePurchase'];

    function InsurancePurchaseDeleteController($uibModalInstance, entity, InsurancePurchase) {
        var vm = this;

        vm.insurancePurchase = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            InsurancePurchase.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
