(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('PurchaseTax', PurchaseTax);

    PurchaseTax.$inject = ['$resource'];

    function PurchaseTax ($resource) {
        var resourceUrl =  'api/purchase-taxes/:id';

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
