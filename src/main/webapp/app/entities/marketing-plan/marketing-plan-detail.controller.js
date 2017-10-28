(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('MarketingPlanDetailController', MarketingPlanDetailController);

    MarketingPlanDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'MarketingPlan'];

    function MarketingPlanDetailController($scope, $rootScope, $stateParams, previousState, entity, MarketingPlan) {
        var vm = this;

        vm.marketingPlan = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:marketingPlanUpdate', function(event, result) {
            vm.marketingPlan = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
