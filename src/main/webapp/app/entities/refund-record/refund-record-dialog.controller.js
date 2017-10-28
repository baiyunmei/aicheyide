(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RefundRecordDialogController', RefundRecordDialogController);

    RefundRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RefundRecord'];

    function RefundRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, RefundRecord) {
        var vm = this;

        vm.refundRecord = entity;
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
            if (vm.refundRecord.id !== null) {
                RefundRecord.update(vm.refundRecord, onSaveSuccess, onSaveError);
            } else {
                RefundRecord.save(vm.refundRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:refundRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
