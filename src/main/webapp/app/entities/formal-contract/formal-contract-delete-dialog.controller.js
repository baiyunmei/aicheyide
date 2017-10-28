(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('FormalContractDeleteController',FormalContractDeleteController);

    FormalContractDeleteController.$inject = ['$uibModalInstance', 'entity', 'FormalContract'];

    function FormalContractDeleteController($uibModalInstance, entity, FormalContract) {
        var vm = this;

        vm.formalContract = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            FormalContract.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
