(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('GasRefitDetailController', GasRefitDetailController);

    GasRefitDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'GasRefit'];

    function GasRefitDetailController($scope, $rootScope, $stateParams, previousState, entity, GasRefit) {
        var vm = this;

        vm.gasRefit = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:gasRefitUpdate', function(event, result) {
            vm.gasRefit = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
