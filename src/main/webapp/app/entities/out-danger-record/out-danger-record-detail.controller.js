(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OutDangerRecordDetailController', OutDangerRecordDetailController);

    OutDangerRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'OutDangerRecord'];

    function OutDangerRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, OutDangerRecord) {
        var vm = this;

        vm.outDangerRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:outDangerRecordUpdate', function(event, result) {
            vm.outDangerRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
