(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('MonthlyManagementDialogController', MonthlyManagementDialogController);

    MonthlyManagementDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'MonthlyManagement'];

    function MonthlyManagementDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, MonthlyManagement) {
        var vm = this;

        vm.monthlyManagement = entity;
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
            if (vm.monthlyManagement.id !== null) {
                MonthlyManagement.update(vm.monthlyManagement, onSaveSuccess, onSaveError);
            } else {
                MonthlyManagement.save(vm.monthlyManagement, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:monthlyManagementUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
