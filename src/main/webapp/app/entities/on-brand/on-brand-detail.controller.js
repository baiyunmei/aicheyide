(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OnBrandDetailController', OnBrandDetailController);

    OnBrandDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'OnBrand'];

    function OnBrandDetailController($scope, $rootScope, $stateParams, previousState, entity, OnBrand) {
        var vm = this;

        vm.onBrand = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:onBrandUpdate', function(event, result) {
            vm.onBrand = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
