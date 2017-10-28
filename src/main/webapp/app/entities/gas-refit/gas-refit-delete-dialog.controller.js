(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('GasRefitDeleteController',GasRefitDeleteController);

    GasRefitDeleteController.$inject = ['$uibModalInstance', 'entity', 'GasRefit'];

    function GasRefitDeleteController($uibModalInstance, entity, GasRefit) {
        var vm = this;

        vm.gasRefit = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            GasRefit.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
