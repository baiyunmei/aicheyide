(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OverdueGatheringDetailController', OverdueGatheringDetailController);

    OverdueGatheringDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'OverdueGathering'];

    function OverdueGatheringDetailController($scope, $rootScope, $stateParams, previousState, entity, OverdueGathering) {
        var vm = this;

        vm.overdueGathering = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:overdueGatheringUpdate', function(event, result) {
            vm.overdueGathering = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
