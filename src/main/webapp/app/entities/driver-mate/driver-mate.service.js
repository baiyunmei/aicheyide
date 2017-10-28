(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('DriverMate', DriverMate);

    DriverMate.$inject = ['$resource'];

    function DriverMate ($resource) {
        var resourceUrl =  'api/driver-mates/:id';

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
