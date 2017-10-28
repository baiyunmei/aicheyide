(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('CommodityDataDialogController', CommodityDataDialogController);

    CommodityDataDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CommodityData'];

    function CommodityDataDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CommodityData) {
        var vm = this;

        vm.commodityData = entity;
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
            if (vm.commodityData.id !== null) {
                CommodityData.update(vm.commodityData, onSaveSuccess, onSaveError);
            } else {
                CommodityData.save(vm.commodityData, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:commodityDataUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
