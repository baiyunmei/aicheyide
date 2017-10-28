(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('FormalContractDetailController', FormalContractDetailController);

    FormalContractDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'FormalContract'];

    function FormalContractDetailController($scope, $rootScope, $stateParams, previousState, entity, FormalContract) {
        var vm = this;

        vm.formalContract = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:formalContractUpdate', function(event, result) {
            vm.formalContract = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
