(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('DownPaymentDialogController', DownPaymentDialogController);

    DownPaymentDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DownPayment'];

    function DownPaymentDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DownPayment) {
        var vm = this;

        vm.downPayment = entity;
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
            if (vm.downPayment.id !== null) {
                DownPayment.update(vm.downPayment, onSaveSuccess, onSaveError);
            } else {
                DownPayment.save(vm.downPayment, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:downPaymentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
