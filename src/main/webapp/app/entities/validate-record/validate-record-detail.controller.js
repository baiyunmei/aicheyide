(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ValidateRecordDetailController', ValidateRecordDetailController);

    ValidateRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ValidateRecord'];

    function ValidateRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, ValidateRecord) {
        var vm = this;

        vm.validateRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:validateRecordUpdate', function(event, result) {
            vm.validateRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
