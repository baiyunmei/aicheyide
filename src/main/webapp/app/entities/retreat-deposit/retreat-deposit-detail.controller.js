(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RetreatDepositDetailController', RetreatDepositDetailController);

    RetreatDepositDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'RetreatDeposit'];

    function RetreatDepositDetailController($scope, $rootScope, $stateParams, previousState, entity, RetreatDeposit) {
        var vm = this;

        vm.retreatDeposit = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:retreatDepositUpdate', function(event, result) {
            vm.retreatDeposit = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
