(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('AuthorizationRecordDialogController', AuthorizationRecordDialogController);

    AuthorizationRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'AuthorizationRecord'];

    function AuthorizationRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, AuthorizationRecord) {
        var vm = this;

        vm.authorizationRecord = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.authorizationRecord.id !== null) {
                AuthorizationRecord.update(vm.authorizationRecord, onSaveSuccess, onSaveError);
            } else {
                AuthorizationRecord.save(vm.authorizationRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:authorizationRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
