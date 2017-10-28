(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('InsurancePurchase', InsurancePurchase);

    InsurancePurchase.$inject = ['$resource'];

    function InsurancePurchase ($resource) {
        var resourceUrl =  'api/insurance-purchases/:id';

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
