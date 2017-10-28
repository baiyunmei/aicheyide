(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('MonthlyManagementDetailController', MonthlyManagementDetailController);

    MonthlyManagementDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'MonthlyManagement'];

    function MonthlyManagementDetailController($scope, $rootScope, $stateParams, previousState, entity, MonthlyManagement) {
        var vm = this;

        vm.monthlyManagement = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:monthlyManagementUpdate', function(event, result) {
            vm.monthlyManagement = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
