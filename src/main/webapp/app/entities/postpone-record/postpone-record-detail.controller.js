(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PostponeRecordDetailController', PostponeRecordDetailController);

    PostponeRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PostponeRecord'];

    function PostponeRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, PostponeRecord) {
        var vm = this;

        vm.postponeRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:postponeRecordUpdate', function(event, result) {
            vm.postponeRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
