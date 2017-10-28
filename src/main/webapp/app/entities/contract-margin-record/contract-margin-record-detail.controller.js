(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ContractMarginRecordDetailController', ContractMarginRecordDetailController);

    ContractMarginRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ContractMarginRecord'];

    function ContractMarginRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, ContractMarginRecord) {
        var vm = this;

        vm.contractMarginRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:contractMarginRecordUpdate', function(event, result) {
            vm.contractMarginRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
