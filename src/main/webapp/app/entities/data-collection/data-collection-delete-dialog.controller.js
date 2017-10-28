(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DataCollectionDeleteController',DataCollectionDeleteController);

    DataCollectionDeleteController.$inject = ['$uibModalInstance', 'entity', 'DataCollection'];

    function DataCollectionDeleteController($uibModalInstance, entity, DataCollection) {
        var vm = this;

        vm.dataCollection = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DataCollection.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
