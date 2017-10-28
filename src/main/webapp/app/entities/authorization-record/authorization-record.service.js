(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('AuthorizationRecord', AuthorizationRecord);

    AuthorizationRecord.$inject = ['$resource'];

    function AuthorizationRecord ($resource) {
        var resourceUrl =  'api/authorization-records/:id';

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
