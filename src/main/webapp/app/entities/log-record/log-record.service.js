(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('LogRecord', LogRecord);

    LogRecord.$inject = ['$resource'];

    function LogRecord ($resource) {
        var resourceUrl =  'api/log-records/:id';

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
