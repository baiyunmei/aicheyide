(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ValidateRecordDialogController', ValidateRecordDialogController);

    ValidateRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ValidateRecord'];

    function ValidateRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ValidateRecord) {
        var vm = this;

        vm.validateRecord = entity;
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
            if (vm.validateRecord.id !== null) {
                ValidateRecord.update(vm.validateRecord, onSaveSuccess, onSaveError);
            } else {
                ValidateRecord.save(vm.validateRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:validateRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
