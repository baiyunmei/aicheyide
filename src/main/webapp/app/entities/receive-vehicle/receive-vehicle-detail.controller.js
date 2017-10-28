(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ReceiveVehicleDetailController', ReceiveVehicleDetailController);

    ReceiveVehicleDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ReceiveVehicle'];

    function ReceiveVehicleDetailController($scope, $rootScope, $stateParams, previousState, entity, ReceiveVehicle) {
        var vm = this;

        vm.receiveVehicle = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:receiveVehicleUpdate', function(event, result) {
            vm.receiveVehicle = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
