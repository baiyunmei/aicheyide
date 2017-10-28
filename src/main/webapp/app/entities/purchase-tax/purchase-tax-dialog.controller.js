(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('PurchaseTaxDialogController', PurchaseTaxDialogController);

    PurchaseTaxDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PurchaseTax'];

    function PurchaseTaxDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PurchaseTax) {
        var vm = this;

        vm.purchaseTax = entity;
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
            if (vm.purchaseTax.id !== null) {
                PurchaseTax.update(vm.purchaseTax, onSaveSuccess, onSaveError);
            } else {
                PurchaseTax.save(vm.purchaseTax, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:purchaseTaxUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
