(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('LogRecordDialogController', LogRecordDialogController);

    LogRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'LogRecord'];

    function LogRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, LogRecord) {
        var vm = this;

        vm.logRecord = entity;
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
            if (vm.logRecord.id !== null) {
                LogRecord.update(vm.logRecord, onSaveSuccess, onSaveError);
            } else {
                LogRecord.save(vm.logRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:logRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
