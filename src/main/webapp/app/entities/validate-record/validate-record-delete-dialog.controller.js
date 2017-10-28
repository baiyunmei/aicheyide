(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ValidateRecordDeleteController',ValidateRecordDeleteController);

    ValidateRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'ValidateRecord'];

    function ValidateRecordDeleteController($uibModalInstance, entity, ValidateRecord) {
        var vm = this;

        vm.validateRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ValidateRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
