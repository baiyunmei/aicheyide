(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('CreditReviewDialogController', CreditReviewDialogController);

    CreditReviewDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CreditReview'];

    function CreditReviewDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CreditReview) {
        var vm = this;

        vm.creditReview = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.creditReview.id !== null) {
                CreditReview.update(vm.creditReview, onSaveSuccess, onSaveError);
            } else {
                CreditReview.save(vm.creditReview, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:creditReviewUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
