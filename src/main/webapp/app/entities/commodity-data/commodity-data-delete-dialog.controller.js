(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('CommodityDataDeleteController',CommodityDataDeleteController);

    CommodityDataDeleteController.$inject = ['$uibModalInstance', 'entity', 'CommodityData'];

    function CommodityDataDeleteController($uibModalInstance, entity, CommodityData) {
        var vm = this;

        vm.commodityData = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CommodityData.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
