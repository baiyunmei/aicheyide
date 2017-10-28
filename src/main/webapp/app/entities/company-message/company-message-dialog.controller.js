(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('CompanyMessageDialogController', CompanyMessageDialogController);

    CompanyMessageDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CompanyMessage'];

    function CompanyMessageDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CompanyMessage) {
        var vm = this;

        vm.companyMessage = entity;
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
            if (vm.companyMessage.id !== null) {
                CompanyMessage.update(vm.companyMessage, onSaveSuccess, onSaveError);
            } else {
                CompanyMessage.save(vm.companyMessage, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:companyMessageUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
