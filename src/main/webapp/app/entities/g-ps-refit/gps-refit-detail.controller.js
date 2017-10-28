(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('GPSRefitDetailController', GPSRefitDetailController);

    GPSRefitDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'GPSRefit'];

    function GPSRefitDetailController($scope, $rootScope, $stateParams, previousState, entity, GPSRefit) {
        var vm = this;

        vm.gPSRefit = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:gPSRefitUpdate', function(event, result) {
            vm.gPSRefit = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
