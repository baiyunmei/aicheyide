(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('FormalContract', FormalContract);

    FormalContract.$inject = ['$resource'];

    function FormalContract ($resource) {
        var resourceUrl =  'api/formal-contracts/:id';

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
