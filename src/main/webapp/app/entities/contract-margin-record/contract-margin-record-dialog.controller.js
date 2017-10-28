(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ContractMarginRecordDialogController', ContractMarginRecordDialogController);

    ContractMarginRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ContractMarginRecord'];

    function ContractMarginRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ContractMarginRecord) {
        var vm = this;

        vm.contractMarginRecord = entity;
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
            if (vm.contractMarginRecord.id !== null) {
                ContractMarginRecord.update(vm.contractMarginRecord, onSaveSuccess, onSaveError);
            } else {
                ContractMarginRecord.save(vm.contractMarginRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:contractMarginRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
