(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RentalManagementDetailController', RentalManagementDetailController);

    RentalManagementDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'RentalManagement'];

    function RentalManagementDetailController($scope, $rootScope, $stateParams, previousState, entity, RentalManagement) {
        var vm = this;

        vm.rentalManagement = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:rentalManagementUpdate', function(event, result) {
            vm.rentalManagement = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
