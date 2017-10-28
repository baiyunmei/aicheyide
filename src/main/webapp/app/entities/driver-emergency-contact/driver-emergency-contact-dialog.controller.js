(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DriverEmergencyContactDialogController', DriverEmergencyContactDialogController);

    DriverEmergencyContactDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DriverEmergencyContact'];

    function DriverEmergencyContactDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DriverEmergencyContact) {
        var vm = this;

        vm.driverEmergencyContact = entity;
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
            if (vm.driverEmergencyContact.id !== null) {
                DriverEmergencyContact.update(vm.driverEmergencyContact, onSaveSuccess, onSaveError);
            } else {
                DriverEmergencyContact.save(vm.driverEmergencyContact, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:driverEmergencyContactUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
