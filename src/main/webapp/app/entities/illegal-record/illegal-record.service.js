(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('IllegalRecord', IllegalRecord);

    IllegalRecord.$inject = ['$resource'];

    function IllegalRecord ($resource) {
        var resourceUrl =  'api/illegal-records/:id';

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
