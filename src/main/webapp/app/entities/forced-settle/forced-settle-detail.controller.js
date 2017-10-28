(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ForcedSettleDetailController', ForcedSettleDetailController);

    ForcedSettleDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ForcedSettle'];

    function ForcedSettleDetailController($scope, $rootScope, $stateParams, previousState, entity, ForcedSettle) {
        var vm = this;

        vm.forcedSettle = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:forcedSettleUpdate', function(event, result) {
            vm.forcedSettle = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
