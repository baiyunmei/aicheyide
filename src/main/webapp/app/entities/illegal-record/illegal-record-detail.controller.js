(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('IllegalRecordDetailController', IllegalRecordDetailController);

    IllegalRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'IllegalRecord'];

    function IllegalRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, IllegalRecord) {
        var vm = this;

        vm.illegalRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:illegalRecordUpdate', function(event, result) {
            vm.illegalRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
