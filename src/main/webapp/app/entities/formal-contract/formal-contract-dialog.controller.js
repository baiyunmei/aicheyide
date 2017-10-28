(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('FormalContractDialogController', FormalContractDialogController);

    FormalContractDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'FormalContract'];

    function FormalContractDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, FormalContract) {
        var vm = this;

        vm.formalContract = entity;
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
            if (vm.formalContract.id !== null) {
                FormalContract.update(vm.formalContract, onSaveSuccess, onSaveError);
            } else {
                FormalContract.save(vm.formalContract, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:formalContractUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
