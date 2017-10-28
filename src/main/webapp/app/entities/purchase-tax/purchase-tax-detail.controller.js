(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PurchaseTaxDetailController', PurchaseTaxDetailController);

    PurchaseTaxDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PurchaseTax'];

    function PurchaseTaxDetailController($scope, $rootScope, $stateParams, previousState, entity, PurchaseTax) {
        var vm = this;

        vm.purchaseTax = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:purchaseTaxUpdate', function(event, result) {
            vm.purchaseTax = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
