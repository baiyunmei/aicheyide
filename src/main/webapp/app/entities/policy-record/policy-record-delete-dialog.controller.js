(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PolicyRecordDeleteController',PolicyRecordDeleteController);

    PolicyRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'PolicyRecord'];

    function PolicyRecordDeleteController($uibModalInstance, entity, PolicyRecord) {
        var vm = this;

        vm.policyRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PolicyRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
