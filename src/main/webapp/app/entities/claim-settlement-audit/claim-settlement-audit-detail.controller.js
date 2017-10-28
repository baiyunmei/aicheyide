(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ClaimSettlementAuditDetailController', ClaimSettlementAuditDetailController);

    ClaimSettlementAuditDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ClaimSettlementAudit'];

    function ClaimSettlementAuditDetailController($scope, $rootScope, $stateParams, previousState, entity, ClaimSettlementAudit) {
        var vm = this;

        vm.claimSettlementAudit = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:claimSettlementAuditUpdate', function(event, result) {
            vm.claimSettlementAudit = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
