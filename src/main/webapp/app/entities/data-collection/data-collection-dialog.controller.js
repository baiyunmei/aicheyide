(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DataCollectionDialogController', DataCollectionDialogController);

    DataCollectionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DataCollection'];

    function DataCollectionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DataCollection) {
        var vm = this;

        vm.dataCollection = entity;
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
            if (vm.dataCollection.id !== null) {
                DataCollection.update(vm.dataCollection, onSaveSuccess, onSaveError);
            } else {
                DataCollection.save(vm.dataCollection, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:dataCollectionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
