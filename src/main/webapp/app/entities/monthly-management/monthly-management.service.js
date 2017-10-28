(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('MonthlyManagement', MonthlyManagement);

    MonthlyManagement.$inject = ['$resource'];

    function MonthlyManagement ($resource) {
        var resourceUrl =  'api/monthly-managements/:id';

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
