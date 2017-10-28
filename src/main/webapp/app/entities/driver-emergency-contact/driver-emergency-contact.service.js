(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('DriverEmergencyContact', DriverEmergencyContact);

    DriverEmergencyContact.$inject = ['$resource'];

    function DriverEmergencyContact ($resource) {
        var resourceUrl =  'api/driver-emergency-contacts/:id';

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
