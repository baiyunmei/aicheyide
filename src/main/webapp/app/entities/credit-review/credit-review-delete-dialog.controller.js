(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('CreditReviewDeleteController',CreditReviewDeleteController);

    CreditReviewDeleteController.$inject = ['$uibModalInstance', 'entity', 'CreditReview'];

    function CreditReviewDeleteController($uibModalInstance, entity, CreditReview) {
        var vm = this;

        vm.creditReview = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CreditReview.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
