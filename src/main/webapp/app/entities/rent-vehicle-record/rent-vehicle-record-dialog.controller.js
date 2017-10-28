(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RentVehicleRecordDialogController', RentVehicleRecordDialogController);

    RentVehicleRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RentVehicleRecord'];

    function RentVehicleRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, RentVehicleRecord) {
        var vm = this;

        vm.rentVehicleRecord = entity;
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
            if (vm.rentVehicleRecord.id !== null) {
                RentVehicleRecord.update(vm.rentVehicleRecord, onSaveSuccess, onSaveError);
            } else {
                RentVehicleRecord.save(vm.rentVehicleRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:rentVehicleRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
