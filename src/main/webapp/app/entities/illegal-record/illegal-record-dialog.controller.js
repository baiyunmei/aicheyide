(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('IllegalRecordDialogController', IllegalRecordDialogController);

    IllegalRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'IllegalRecord'];

    function IllegalRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, IllegalRecord) {
        var vm = this;

        vm.illegalRecord = entity;
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
            if (vm.illegalRecord.id !== null) {
                IllegalRecord.update(vm.illegalRecord, onSaveSuccess, onSaveError);
            } else {
                IllegalRecord.save(vm.illegalRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:illegalRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
