(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('LogDeleteController',LogDeleteController);

    LogDeleteController.$inject = ['$uibModalInstance', 'entity', 'Log'];

    function LogDeleteController($uibModalInstance, entity, Log) {
        var vm = this;

        vm.log = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Log.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
