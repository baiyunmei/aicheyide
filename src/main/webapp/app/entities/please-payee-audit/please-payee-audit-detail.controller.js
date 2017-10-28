(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PleasePayeeAuditDetailController', PleasePayeeAuditDetailController);

    PleasePayeeAuditDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PleasePayeeAudit'];

    function PleasePayeeAuditDetailController($scope, $rootScope, $stateParams, previousState, entity, PleasePayeeAudit) {
        var vm = this;

        vm.pleasePayeeAudit = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:pleasePayeeAuditUpdate', function(event, result) {
            vm.pleasePayeeAudit = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
