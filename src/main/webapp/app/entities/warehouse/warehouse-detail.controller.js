(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('WarehouseDetailController', WarehouseDetailController);

    WarehouseDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Warehouse'];

    function WarehouseDetailController($scope, $rootScope, $stateParams, previousState, entity, Warehouse) {
        var vm = this;

        vm.warehouse = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:warehouseUpdate', function(event, result) {
            vm.warehouse = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
