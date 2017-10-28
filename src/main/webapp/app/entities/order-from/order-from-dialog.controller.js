(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('OrderFromDialogController', OrderFromDialogController);

    OrderFromDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'OrderFrom'];

    function OrderFromDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, OrderFrom) {
        var vm = this;

        vm.orderFrom = entity;
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
            if (vm.orderFrom.id !== null) {
                OrderFrom.update(vm.orderFrom, onSaveSuccess, onSaveError);
            } else {
                OrderFrom.save(vm.orderFrom, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:orderFromUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
