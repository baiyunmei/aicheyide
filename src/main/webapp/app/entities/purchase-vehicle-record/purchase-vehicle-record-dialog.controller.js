(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PurchaseVehicleRecordDialogController', PurchaseVehicleRecordDialogController);

    PurchaseVehicleRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PurchaseVehicleRecord'];

    function PurchaseVehicleRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PurchaseVehicleRecord) {
        var vm = this;

        vm.purchaseVehicleRecord = entity;
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
            if (vm.purchaseVehicleRecord.id !== null) {
                PurchaseVehicleRecord.update(vm.purchaseVehicleRecord, onSaveSuccess, onSaveError);
            } else {
                PurchaseVehicleRecord.save(vm.purchaseVehicleRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:purchaseVehicleRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
