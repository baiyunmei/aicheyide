(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RefundRecordDetailController', RefundRecordDetailController);

    RefundRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'RefundRecord'];

    function RefundRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, RefundRecord) {
        var vm = this;

        vm.refundRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:refundRecordUpdate', function(event, result) {
            vm.refundRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
