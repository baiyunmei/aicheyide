(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DepositContractDetailController', DepositContractDetailController);

    DepositContractDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DepositContract'];

    function DepositContractDetailController($scope, $rootScope, $stateParams, previousState, entity, DepositContract) {
        var vm = this;

        vm.depositContract = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:depositContractUpdate', function(event, result) {
            vm.depositContract = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
