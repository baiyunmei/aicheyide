(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('CommodityDataDetailController', CommodityDataDetailController);

    CommodityDataDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CommodityData'];

    function CommodityDataDetailController($scope, $rootScope, $stateParams, previousState, entity, CommodityData) {
        var vm = this;

        vm.commodityData = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:commodityDataUpdate', function(event, result) {
            vm.commodityData = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
