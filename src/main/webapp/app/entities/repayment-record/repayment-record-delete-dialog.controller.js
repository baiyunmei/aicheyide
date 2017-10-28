(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RepaymentRecordDeleteController',RepaymentRecordDeleteController);

    RepaymentRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'RepaymentRecord'];

    function RepaymentRecordDeleteController($uibModalInstance, entity, RepaymentRecord) {
        var vm = this;

        vm.repaymentRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            RepaymentRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
