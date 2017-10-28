(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('AdditionalMaterialsDialogController', AdditionalMaterialsDialogController);

    AdditionalMaterialsDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'AdditionalMaterials'];

    function AdditionalMaterialsDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, AdditionalMaterials) {
        var vm = this;

        vm.additionalMaterials = entity;
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
            if (vm.additionalMaterials.id !== null) {
                AdditionalMaterials.update(vm.additionalMaterials, onSaveSuccess, onSaveError);
            } else {
                AdditionalMaterials.save(vm.additionalMaterials, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:additionalMaterialsUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
