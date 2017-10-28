(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('ForcedSettleDialogController', ForcedSettleDialogController);

    ForcedSettleDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ForcedSettle'];

    function ForcedSettleDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ForcedSettle) {
        var vm = this;

        vm.forcedSettle = entity;
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
            if (vm.forcedSettle.id !== null) {
                ForcedSettle.update(vm.forcedSettle, onSaveSuccess, onSaveError);
            } else {
                ForcedSettle.save(vm.forcedSettle, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:forcedSettleUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
