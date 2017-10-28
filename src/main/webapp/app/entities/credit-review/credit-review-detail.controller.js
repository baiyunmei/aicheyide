(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('CreditReviewDetailController', CreditReviewDetailController);

    CreditReviewDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CreditReview'];

    function CreditReviewDetailController($scope, $rootScope, $stateParams, previousState, entity, CreditReview) {
        var vm = this;

        vm.creditReview = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:creditReviewUpdate', function(event, result) {
            vm.creditReview = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
