(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DriverMessageDialogController', DriverMessageDialogController);

    DriverMessageDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DriverMessage'];

    function DriverMessageDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DriverMessage) {
        var vm = this;

        vm.driverMessage = entity;
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
            if (vm.driverMessage.id !== null) {
                DriverMessage.update(vm.driverMessage, onSaveSuccess, onSaveError);
            } else {
                DriverMessage.save(vm.driverMessage, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:driverMessageUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
