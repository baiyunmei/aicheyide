(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('MaintainRecordDeleteController',MaintainRecordDeleteController);

    MaintainRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'MaintainRecord'];

    function MaintainRecordDeleteController($uibModalInstance, entity, MaintainRecord) {
        var vm = this;

        vm.maintainRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            MaintainRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
