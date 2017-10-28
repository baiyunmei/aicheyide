(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OutDangerRecordDialogController', OutDangerRecordDialogController);

    OutDangerRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'OutDangerRecord'];

    function OutDangerRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, OutDangerRecord) {
        var vm = this;

        vm.outDangerRecord = entity;
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
            if (vm.outDangerRecord.id !== null) {
                OutDangerRecord.update(vm.outDangerRecord, onSaveSuccess, onSaveError);
            } else {
                OutDangerRecord.save(vm.outDangerRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:outDangerRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
