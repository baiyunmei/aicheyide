(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DriverMessageDetailController', DriverMessageDetailController);

    DriverMessageDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DriverMessage'];

    function DriverMessageDetailController($scope, $rootScope, $stateParams, previousState, entity, DriverMessage) {
        var vm = this;

        vm.driverMessage = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:driverMessageUpdate', function(event, result) {
            vm.driverMessage = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
