(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('WarehouseDialogController', WarehouseDialogController);

    WarehouseDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Warehouse'];

    function WarehouseDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Warehouse) {
        var vm = this;

        vm.warehouse = entity;
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
            if (vm.warehouse.id !== null) {
                Warehouse.update(vm.warehouse, onSaveSuccess, onSaveError);
            } else {
                Warehouse.save(vm.warehouse, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:warehouseUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
