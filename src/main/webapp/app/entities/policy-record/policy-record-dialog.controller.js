(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PolicyRecordDialogController', PolicyRecordDialogController);

    PolicyRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PolicyRecord'];

    function PolicyRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PolicyRecord) {
        var vm = this;

        vm.policyRecord = entity;
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
            if (vm.policyRecord.id !== null) {
                PolicyRecord.update(vm.policyRecord, onSaveSuccess, onSaveError);
            } else {
                PolicyRecord.save(vm.policyRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:policyRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
