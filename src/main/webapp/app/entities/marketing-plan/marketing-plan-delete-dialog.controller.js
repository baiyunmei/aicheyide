(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('MarketingPlanDeleteController',MarketingPlanDeleteController);

    MarketingPlanDeleteController.$inject = ['$uibModalInstance', 'entity', 'MarketingPlan'];

    function MarketingPlanDeleteController($uibModalInstance, entity, MarketingPlan) {
        var vm = this;

        vm.marketingPlan = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            MarketingPlan.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
