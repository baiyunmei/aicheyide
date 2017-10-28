(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PostDetailController', PostDetailController);

    PostDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Post'];

    function PostDetailController($scope, $rootScope, $stateParams, previousState, entity, Post) {
        var vm = this;

        vm.post = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:postUpdate', function(event, result) {
            vm.post = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
