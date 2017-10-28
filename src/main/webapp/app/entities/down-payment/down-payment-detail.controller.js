(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DownPaymentDetailController', DownPaymentDetailController);

    DownPaymentDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DownPayment'];

    function DownPaymentDetailController($scope, $rootScope, $stateParams, previousState, entity, DownPayment) {
        var vm = this;

        vm.downPayment = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:downPaymentUpdate', function(event, result) {
            vm.downPayment = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
