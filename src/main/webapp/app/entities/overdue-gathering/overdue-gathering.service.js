(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('OverdueGathering', OverdueGathering);

    OverdueGathering.$inject = ['$resource'];

    function OverdueGathering ($resource) {
        var resourceUrl =  'api/overdue-gatherings/:id';

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
