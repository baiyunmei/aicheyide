(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ForcedSettleDeleteController',ForcedSettleDeleteController);

    ForcedSettleDeleteController.$inject = ['$uibModalInstance', 'entity', 'ForcedSettle'];

    function ForcedSettleDeleteController($uibModalInstance, entity, ForcedSettle) {
        var vm = this;

        vm.forcedSettle = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ForcedSettle.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
