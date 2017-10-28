(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OrderFromDetailController', OrderFromDetailController);

    OrderFromDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'OrderFrom'];

    function OrderFromDetailController($scope, $rootScope, $stateParams, previousState, entity, OrderFrom) {
        var vm = this;

        vm.orderFrom = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:orderFromUpdate', function(event, result) {
            vm.orderFrom = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
