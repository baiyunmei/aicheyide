(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OverdueManagementDetailController', OverdueManagementDetailController);

    OverdueManagementDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'OverdueManagement'];

    function OverdueManagementDetailController($scope, $rootScope, $stateParams, previousState, entity, OverdueManagement) {
        var vm = this;

        vm.overdueManagement = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:overdueManagementUpdate', function(event, result) {
            vm.overdueManagement = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
