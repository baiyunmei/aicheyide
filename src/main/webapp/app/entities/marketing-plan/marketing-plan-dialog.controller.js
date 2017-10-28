(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('MarketingPlanDialogController', MarketingPlanDialogController);

    MarketingPlanDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'MarketingPlan'];

    function MarketingPlanDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, MarketingPlan) {
        var vm = this;

        vm.marketingPlan = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.marketingPlan.id !== null) {
                MarketingPlan.update(vm.marketingPlan, onSaveSuccess, onSaveError);
            } else {
                MarketingPlan.save(vm.marketingPlan, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:marketingPlanUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
