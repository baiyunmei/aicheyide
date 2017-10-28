(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RentVehicleRecordDetailController', RentVehicleRecordDetailController);

    RentVehicleRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'RentVehicleRecord'];

    function RentVehicleRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, RentVehicleRecord) {
        var vm = this;

        vm.rentVehicleRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:rentVehicleRecordUpdate', function(event, result) {
            vm.rentVehicleRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
