(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('AuthorizationRecordDetailController', AuthorizationRecordDetailController);

    AuthorizationRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'AuthorizationRecord'];

    function AuthorizationRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, AuthorizationRecord) {
        var vm = this;

        vm.authorizationRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:authorizationRecordUpdate', function(event, result) {
            vm.authorizationRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
