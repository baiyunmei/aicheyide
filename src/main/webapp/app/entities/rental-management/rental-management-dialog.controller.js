(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('RentalManagementDialogController', RentalManagementDialogController);

    RentalManagementDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'RentalManagement'];

    function RentalManagementDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, RentalManagement) {
        var vm = this;

        vm.rentalManagement = entity;
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
            if (vm.rentalManagement.id !== null) {
                RentalManagement.update(vm.rentalManagement, onSaveSuccess, onSaveError);
            } else {
                RentalManagement.save(vm.rentalManagement, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:rentalManagementUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
