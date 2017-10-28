(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('MaintainRecordDetailController', MaintainRecordDetailController);

    MaintainRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'MaintainRecord'];

    function MaintainRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, MaintainRecord) {
        var vm = this;

        vm.maintainRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:maintainRecordUpdate', function(event, result) {
            vm.maintainRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
