(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('AiRecordDeleteController',AiRecordDeleteController);

    AiRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'AiRecord'];

    function AiRecordDeleteController($uibModalInstance, entity, AiRecord) {
        var vm = this;

        vm.aiRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            AiRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
