(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DriverEmergencyContactDetailController', DriverEmergencyContactDetailController);

    DriverEmergencyContactDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DriverEmergencyContact'];

    function DriverEmergencyContactDetailController($scope, $rootScope, $stateParams, previousState, entity, DriverEmergencyContact) {
        var vm = this;

        vm.driverEmergencyContact = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:driverEmergencyContactUpdate', function(event, result) {
            vm.driverEmergencyContact = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
