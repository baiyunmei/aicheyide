(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ContractMarginRecordDeleteController',ContractMarginRecordDeleteController);

    ContractMarginRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'ContractMarginRecord'];

    function ContractMarginRecordDeleteController($uibModalInstance, entity, ContractMarginRecord) {
        var vm = this;

        vm.contractMarginRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ContractMarginRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
