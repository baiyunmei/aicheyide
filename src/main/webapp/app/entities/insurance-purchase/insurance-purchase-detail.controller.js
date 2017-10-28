(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('InsurancePurchaseDetailController', InsurancePurchaseDetailController);

    InsurancePurchaseDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'InsurancePurchase'];

    function InsurancePurchaseDetailController($scope, $rootScope, $stateParams, previousState, entity, InsurancePurchase) {
        var vm = this;

        vm.insurancePurchase = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:insurancePurchaseUpdate', function(event, result) {
            vm.insurancePurchase = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
