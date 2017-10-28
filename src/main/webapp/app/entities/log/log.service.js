(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('Log', Log);

    Log.$inject = ['$resource'];

    function Log ($resource) {
        var resourceUrl =  'api/logs/:id';

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
