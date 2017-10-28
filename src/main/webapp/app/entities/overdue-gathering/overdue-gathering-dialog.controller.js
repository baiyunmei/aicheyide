(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OverdueGatheringDialogController', OverdueGatheringDialogController);

    OverdueGatheringDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'OverdueGathering'];

    function OverdueGatheringDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, OverdueGathering) {
        var vm = this;

        vm.overdueGathering = entity;
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
            if (vm.overdueGathering.id !== null) {
                OverdueGathering.update(vm.overdueGathering, onSaveSuccess, onSaveError);
            } else {
                OverdueGathering.save(vm.overdueGathering, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:overdueGatheringUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
