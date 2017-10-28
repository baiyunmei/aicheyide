(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('LogRecordDeleteController',LogRecordDeleteController);

    LogRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'LogRecord'];

    function LogRecordDeleteController($uibModalInstance, entity, LogRecord) {
        var vm = this;

        vm.logRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            LogRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
