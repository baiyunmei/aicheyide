(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('LogDetailController', LogDetailController);

    LogDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Log'];

    function LogDetailController($scope, $rootScope, $stateParams, previousState, entity, Log) {
        var vm = this;

        vm.log = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:logUpdate', function(event, result) {
            vm.log = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
