(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('CompanyMessageDetailController', CompanyMessageDetailController);

    CompanyMessageDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CompanyMessage'];

    function CompanyMessageDetailController($scope, $rootScope, $stateParams, previousState, entity, CompanyMessage) {
        var vm = this;

        vm.companyMessage = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:companyMessageUpdate', function(event, result) {
            vm.companyMessage = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
