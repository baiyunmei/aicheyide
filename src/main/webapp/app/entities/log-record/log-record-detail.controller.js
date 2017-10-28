(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('LogRecordDetailController', LogRecordDetailController);

    LogRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LogRecord'];

    function LogRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, LogRecord) {
        var vm = this;

        vm.logRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:logRecordUpdate', function(event, result) {
            vm.logRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
