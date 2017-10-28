(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('LogDialogController', LogDialogController);

    LogDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Log'];

    function LogDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Log) {
        var vm = this;

        vm.log = entity;
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
            if (vm.log.id !== null) {
                Log.update(vm.log, onSaveSuccess, onSaveError);
            } else {
                Log.save(vm.log, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:logUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
