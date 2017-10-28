(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('MaintainRecordDialogController', MaintainRecordDialogController);

    MaintainRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'MaintainRecord'];

    function MaintainRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, MaintainRecord) {
        var vm = this;

        vm.maintainRecord = entity;
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
            if (vm.maintainRecord.id !== null) {
                MaintainRecord.update(vm.maintainRecord, onSaveSuccess, onSaveError);
            } else {
                MaintainRecord.save(vm.maintainRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:maintainRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
