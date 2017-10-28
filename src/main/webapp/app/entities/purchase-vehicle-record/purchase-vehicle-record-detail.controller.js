(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PurchaseVehicleRecordDetailController', PurchaseVehicleRecordDetailController);

    PurchaseVehicleRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PurchaseVehicleRecord'];

    function PurchaseVehicleRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, PurchaseVehicleRecord) {
        var vm = this;

        vm.purchaseVehicleRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:purchaseVehicleRecordUpdate', function(event, result) {
            vm.purchaseVehicleRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
