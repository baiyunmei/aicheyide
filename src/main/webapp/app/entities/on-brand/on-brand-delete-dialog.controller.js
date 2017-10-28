(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OnBrandDeleteController',OnBrandDeleteController);

    OnBrandDeleteController.$inject = ['$uibModalInstance', 'entity', 'OnBrand'];

    function OnBrandDeleteController($uibModalInstance, entity, OnBrand) {
        var vm = this;

        vm.onBrand = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            OnBrand.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
