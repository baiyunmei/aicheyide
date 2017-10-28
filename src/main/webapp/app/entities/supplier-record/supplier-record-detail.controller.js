(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .controller('SupplierRecordDetailController', SupplierRecordDetailController);

    SupplierRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'SupplierRecord'];

    function SupplierRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, SupplierRecord) {
        var vm = this;

        vm.supplierRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('aicheyideApp:supplierRecordUpdate', function(event, result) {
            vm.supplierRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
