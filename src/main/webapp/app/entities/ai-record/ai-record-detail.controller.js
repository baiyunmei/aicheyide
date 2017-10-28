(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('AiRecordDetailController', AiRecordDetailController);

    AiRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'AiRecord'];

    function AiRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, AiRecord) {
        var vm = this;

        vm.aiRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:aiRecordUpdate', function(event, result) {
            vm.aiRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
