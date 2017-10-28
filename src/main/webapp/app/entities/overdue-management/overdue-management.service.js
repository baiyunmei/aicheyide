(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('OverdueManagement', OverdueManagement);

    OverdueManagement.$inject = ['$resource'];

    function OverdueManagement ($resource) {
        var resourceUrl =  'api/overdue-managements/:id';

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
