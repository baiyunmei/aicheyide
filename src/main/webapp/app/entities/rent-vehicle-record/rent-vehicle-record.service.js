(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('RentVehicleRecord', RentVehicleRecord);

    RentVehicleRecord.$inject = ['$resource'];

    function RentVehicleRecord ($resource) {
        var resourceUrl =  'api/rent-vehicle-records/:id';

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
