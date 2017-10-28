(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('SettleApplyDetailController', SettleApplyDetailController);

    SettleApplyDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'SettleApply'];

    function SettleApplyDetailController($scope, $rootScope, $stateParams, previousState, entity, SettleApply) {
        var vm = this;

        vm.settleApply = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:settleApplyUpdate', function(event, result) {
            vm.settleApply = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
