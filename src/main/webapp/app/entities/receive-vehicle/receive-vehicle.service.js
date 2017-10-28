(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('ReceiveVehicle', ReceiveVehicle);

    ReceiveVehicle.$inject = ['$resource'];

    function ReceiveVehicle ($resource) {
        var resourceUrl =  'api/receive-vehicles/:id';

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
