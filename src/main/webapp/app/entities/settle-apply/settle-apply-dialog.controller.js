(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('SettleApplyDialogController', SettleApplyDialogController);

    SettleApplyDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'SettleApply'];

    function SettleApplyDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, SettleApply) {
        var vm = this;

        vm.settleApply = entity;
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
            if (vm.settleApply.id !== null) {
                SettleApply.update(vm.settleApply, onSaveSuccess, onSaveError);
            } else {
                SettleApply.save(vm.settleApply, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:settleApplyUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
