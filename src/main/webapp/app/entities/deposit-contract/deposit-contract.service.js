(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('DepositContract', DepositContract);

    DepositContract.$inject = ['$resource'];

    function DepositContract ($resource) {
        var resourceUrl =  'api/deposit-contracts/:id';

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
