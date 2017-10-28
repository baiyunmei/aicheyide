(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RefundRecordDeleteController',RefundRecordDeleteController);

    RefundRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'RefundRecord'];

    function RefundRecordDeleteController($uibModalInstance, entity, RefundRecord) {
        var vm = this;

        vm.refundRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RefundRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
