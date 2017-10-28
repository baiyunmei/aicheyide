(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('ContractMarginRecord', ContractMarginRecord);

    ContractMarginRecord.$inject = ['$resource'];

    function ContractMarginRecord ($resource) {
        var resourceUrl =  'api/contract-margin-records/:id';

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
