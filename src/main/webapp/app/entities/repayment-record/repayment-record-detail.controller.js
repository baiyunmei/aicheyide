(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RepaymentRecordDetailController', RepaymentRecordDetailController);

    RepaymentRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'RepaymentRecord'];

    function RepaymentRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, RepaymentRecord) {
        var vm = this;

        vm.repaymentRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:repaymentRecordUpdate', function(event, result) {
            vm.repaymentRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
