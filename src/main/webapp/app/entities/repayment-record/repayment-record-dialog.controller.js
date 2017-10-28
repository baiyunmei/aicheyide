(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RepaymentRecordDialogController', RepaymentRecordDialogController);

    RepaymentRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RepaymentRecord'];

    function RepaymentRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, RepaymentRecord) {
        var vm = this;

        vm.repaymentRecord = entity;
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
            if (vm.repaymentRecord.id !== null) {
                RepaymentRecord.update(vm.repaymentRecord, onSaveSuccess, onSaveError);
            } else {
                RepaymentRecord.save(vm.repaymentRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:repaymentRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
