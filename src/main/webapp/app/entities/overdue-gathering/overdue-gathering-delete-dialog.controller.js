(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OverdueGatheringDeleteController',OverdueGatheringDeleteController);

    OverdueGatheringDeleteController.$inject = ['$uibModalInstance', 'entity', 'OverdueGathering'];

    function OverdueGatheringDeleteController($uibModalInstance, entity, OverdueGathering) {
        var vm = this;

        vm.overdueGathering = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            OverdueGathering.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
