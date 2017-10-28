(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('BuyVehicleRecordDialogController', BuyVehicleRecordDialogController);

    BuyVehicleRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'BuyVehicleRecord'];

    function BuyVehicleRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, BuyVehicleRecord) {
        var vm = this;

        vm.buyVehicleRecord = entity;
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
            if (vm.buyVehicleRecord.id !== null) {
                BuyVehicleRecord.update(vm.buyVehicleRecord, onSaveSuccess, onSaveError);
            } else {
                BuyVehicleRecord.save(vm.buyVehicleRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:buyVehicleRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
