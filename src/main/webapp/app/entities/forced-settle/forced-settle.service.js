(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('ForcedSettle', ForcedSettle);

    ForcedSettle.$inject = ['$resource'];

    function ForcedSettle ($resource) {
        var resourceUrl =  'api/forced-settles/:id';

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
