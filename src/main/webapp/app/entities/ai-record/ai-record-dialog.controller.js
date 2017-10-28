(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('AiRecordDialogController', AiRecordDialogController);

    AiRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'AiRecord'];

    function AiRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, AiRecord) {
        var vm = this;

        vm.aiRecord = entity;
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
            if (vm.aiRecord.id !== null) {
                AiRecord.update(vm.aiRecord, onSaveSuccess, onSaveError);
            } else {
                AiRecord.save(vm.aiRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:aiRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
