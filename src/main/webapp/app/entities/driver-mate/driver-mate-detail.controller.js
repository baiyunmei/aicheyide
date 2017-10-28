(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DriverMateDetailController', DriverMateDetailController);

    DriverMateDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DriverMate'];

    function DriverMateDetailController($scope, $rootScope, $stateParams, previousState, entity, DriverMate) {
        var vm = this;

        vm.driverMate = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:driverMateUpdate', function(event, result) {
            vm.driverMate = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
