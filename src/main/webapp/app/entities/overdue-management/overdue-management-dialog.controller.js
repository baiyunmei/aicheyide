(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OverdueManagementDialogController', OverdueManagementDialogController);

    OverdueManagementDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'OverdueManagement'];

    function OverdueManagementDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, OverdueManagement) {
        var vm = this;

        vm.overdueManagement = entity;
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
            if (vm.overdueManagement.id !== null) {
                OverdueManagement.update(vm.overdueManagement, onSaveSuccess, onSaveError);
            } else {
                OverdueManagement.save(vm.overdueManagement, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:overdueManagementUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
