(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('SupplierRecordDialogController', SupplierRecordDialogController);

    SupplierRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'SupplierRecord'];

    function SupplierRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, SupplierRecord) {
        var vm = this;

        vm.supplierRecord = entity;
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
            if (vm.supplierRecord.id !== null) {
                SupplierRecord.update(vm.supplierRecord, onSaveSuccess, onSaveError);
            } else {
                SupplierRecord.save(vm.supplierRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:supplierRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
