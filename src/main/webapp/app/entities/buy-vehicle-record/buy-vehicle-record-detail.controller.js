(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('BuyVehicleRecordDetailController', BuyVehicleRecordDetailController);

    BuyVehicleRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'BuyVehicleRecord'];

    function BuyVehicleRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, BuyVehicleRecord) {
        var vm = this;

        vm.buyVehicleRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:buyVehicleRecordUpdate', function(event, result) {
            vm.buyVehicleRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
