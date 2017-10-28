(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('GPSRefitDialogController', GPSRefitDialogController);

    GPSRefitDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'GPSRefit'];

    function GPSRefitDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, GPSRefit) {
        var vm = this;

        vm.gPSRefit = entity;
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
            if (vm.gPSRefit.id !== null) {
                GPSRefit.update(vm.gPSRefit, onSaveSuccess, onSaveError);
            } else {
                GPSRefit.save(vm.gPSRefit, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('aicheyideApp:gPSRefitUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
