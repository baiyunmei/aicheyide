(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('AuthorizationRecordDeleteController',AuthorizationRecordDeleteController);

    AuthorizationRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'AuthorizationRecord'];

    function AuthorizationRecordDeleteController($uibModalInstance, entity, AuthorizationRecord) {
        var vm = this;

        vm.authorizationRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            AuthorizationRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
