(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('SettleApplyDeleteController',SettleApplyDeleteController);

    SettleApplyDeleteController.$inject = ['$uibModalInstance', 'entity', 'SettleApply'];

    function SettleApplyDeleteController($uibModalInstance, entity, SettleApply) {
        var vm = this;

        vm.settleApply = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            SettleApply.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
