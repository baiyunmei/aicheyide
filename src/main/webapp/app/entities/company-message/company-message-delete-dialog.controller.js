(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('CompanyMessageDeleteController',CompanyMessageDeleteController);

    CompanyMessageDeleteController.$inject = ['$uibModalInstance', 'entity', 'CompanyMessage'];

    function CompanyMessageDeleteController($uibModalInstance, entity, CompanyMessage) {
        var vm = this;

        vm.companyMessage = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CompanyMessage.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
