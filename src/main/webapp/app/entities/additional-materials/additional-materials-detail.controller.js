(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('AdditionalMaterialsDetailController', AdditionalMaterialsDetailController);

    AdditionalMaterialsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'AdditionalMaterials'];

    function AdditionalMaterialsDetailController($scope, $rootScope, $stateParams, previousState, entity, AdditionalMaterials) {
        var vm = this;

        vm.additionalMaterials = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:additionalMaterialsUpdate', function(event, result) {
            vm.additionalMaterials = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
