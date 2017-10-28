(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('PolicyRecord', PolicyRecord);

    PolicyRecord.$inject = ['$resource'];

    function PolicyRecord ($resource) {
        var resourceUrl =  'api/policy-records/:id';

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
