(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('PurchaseVehicleRecord', PurchaseVehicleRecord);

    PurchaseVehicleRecord.$inject = ['$resource'];

    function PurchaseVehicleRecord ($resource) {
        var resourceUrl =  'api/purchase-vehicle-records/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
