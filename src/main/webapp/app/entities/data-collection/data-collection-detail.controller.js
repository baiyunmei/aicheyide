(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DataCollectionDetailController', DataCollectionDetailController);

    DataCollectionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DataCollection'];

    function DataCollectionDetailController($scope, $rootScope, $stateParams, previousState, entity, DataCollection) {
        var vm = this;

        vm.dataCollection = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:dataCollectionUpdate', function(event, result) {
            vm.dataCollection = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
