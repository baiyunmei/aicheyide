(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('GPSRefitDeleteController',GPSRefitDeleteController);

    GPSRefitDeleteController.$inject = ['$uibModalInstance', 'entity', 'GPSRefit'];

    function GPSRefitDeleteController($uibModalInstance, entity, GPSRefit) {
        var vm = this;

        vm.gPSRefit = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            GPSRefit.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
