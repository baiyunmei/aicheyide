(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PleasePayeeAuditDialogController', PleasePayeeAuditDialogController);

    PleasePayeeAuditDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PleasePayeeAudit'];

    function PleasePayeeAuditDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PleasePayeeAudit) {
        var vm = this;

        vm.pleasePayeeAudit = entity;
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
            if (vm.pleasePayeeAudit.id !== null) {
                PleasePayeeAudit.update(vm.pleasePayeeAudit, onSaveSuccess, onSaveError);
            } else {
                PleasePayeeAudit.save(vm.pleasePayeeAudit, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:pleasePayeeAuditUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
