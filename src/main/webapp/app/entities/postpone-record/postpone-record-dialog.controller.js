(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PostponeRecordDialogController', PostponeRecordDialogController);

    PostponeRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PostponeRecord'];

    function PostponeRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PostponeRecord) {
        var vm = this;

        vm.postponeRecord = entity;
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
            if (vm.postponeRecord.id !== null) {
                PostponeRecord.update(vm.postponeRecord, onSaveSuccess, onSaveError);
            } else {
                PostponeRecord.save(vm.postponeRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:postponeRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
