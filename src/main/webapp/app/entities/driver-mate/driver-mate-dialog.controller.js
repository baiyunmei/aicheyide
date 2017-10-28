(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DriverMateDialogController', DriverMateDialogController);

    DriverMateDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DriverMate'];

    function DriverMateDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DriverMate) {
        var vm = this;

        vm.driverMate = entity;
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
            if (vm.driverMate.id !== null) {
                DriverMate.update(vm.driverMate, onSaveSuccess, onSaveError);
            } else {
                DriverMate.save(vm.driverMate, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:driverMateUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
