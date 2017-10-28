(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ClaimSettlementAuditDialogController', ClaimSettlementAuditDialogController);

    ClaimSettlementAuditDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ClaimSettlementAudit'];

    function ClaimSettlementAuditDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ClaimSettlementAudit) {
        var vm = this;

        vm.claimSettlementAudit = entity;
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
            if (vm.claimSettlementAudit.id !== null) {
                ClaimSettlementAudit.update(vm.claimSettlementAudit, onSaveSuccess, onSaveError);
            } else {
                ClaimSettlementAudit.save(vm.claimSettlementAudit, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:claimSettlementAuditUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
