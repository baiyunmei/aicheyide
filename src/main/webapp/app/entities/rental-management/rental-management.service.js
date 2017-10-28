(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('RentalManagement', RentalManagement);

    RentalManagement.$inject = ['$resource'];

    function RentalManagement ($resource) {
        var resourceUrl =  'api/rental-managements/:id';

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
