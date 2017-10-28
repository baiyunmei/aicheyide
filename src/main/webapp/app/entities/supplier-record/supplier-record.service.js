(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('SupplierRecord', SupplierRecord);

    SupplierRecord.$inject = ['$resource'];

    function SupplierRecord ($resource) {
        var resourceUrl =  'api/supplier-records/:id';

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
