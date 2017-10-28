(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('AiRecord', AiRecord);

    AiRecord.$inject = ['$resource'];

    function AiRecord ($resource) {
        var resourceUrl =  'api/ai-records/:id';

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
