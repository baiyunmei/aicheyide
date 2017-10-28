(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PostponeRecordDeleteController',PostponeRecordDeleteController);

    PostponeRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'PostponeRecord'];

    function PostponeRecordDeleteController($uibModalInstance, entity, PostponeRecord) {
        var vm = this;

        vm.postponeRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PostponeRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
