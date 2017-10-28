(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('IllegalRecordDeleteController',IllegalRecordDeleteController);

    IllegalRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'IllegalRecord'];

    function IllegalRecordDeleteController($uibModalInstance, entity, IllegalRecord) {
        var vm = this;

        vm.illegalRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            IllegalRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
