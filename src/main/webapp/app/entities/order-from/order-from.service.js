(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('OrderFrom', OrderFrom);

    OrderFrom.$inject = ['$resource'];

    function OrderFrom ($resource) {
        var resourceUrl =  'api/order-froms/:id';

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
