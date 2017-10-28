(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('AdditionalMaterialsDeleteController',AdditionalMaterialsDeleteController);

    AdditionalMaterialsDeleteController.$inject = ['$uibModalInstance', 'entity', 'AdditionalMaterials'];

    function AdditionalMaterialsDeleteController($uibModalInstance, entity, AdditionalMaterials) {
        var vm = this;

        vm.additionalMaterials = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            AdditionalMaterials.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
