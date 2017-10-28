(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OutDangerRecordDeleteController',OutDangerRecordDeleteController);

    OutDangerRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'OutDangerRecord'];

    function OutDangerRecordDeleteController($uibModalInstance, entity, OutDangerRecord) {
        var vm = this;

        vm.outDangerRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            OutDangerRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
