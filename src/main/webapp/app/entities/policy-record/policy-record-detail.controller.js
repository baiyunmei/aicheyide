(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PolicyRecordDetailController', PolicyRecordDetailController);

    PolicyRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PolicyRecord'];

    function PolicyRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, PolicyRecord) {
        var vm = this;

        vm.policyRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:policyRecordUpdate', function(event, result) {
            vm.policyRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
