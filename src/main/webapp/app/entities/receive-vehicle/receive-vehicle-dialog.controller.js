(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ReceiveVehicleDialogController', ReceiveVehicleDialogController);

    ReceiveVehicleDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ReceiveVehicle'];

    function ReceiveVehicleDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ReceiveVehicle) {
        var vm = this;

        vm.receiveVehicle = entity;
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
            if (vm.receiveVehicle.id !== null) {
                ReceiveVehicle.update(vm.receiveVehicle, onSaveSuccess, onSaveError);
            } else {
                ReceiveVehicle.save(vm.receiveVehicle, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:receiveVehicleUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
