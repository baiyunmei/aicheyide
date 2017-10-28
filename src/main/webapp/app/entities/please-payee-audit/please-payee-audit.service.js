(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('PleasePayeeAudit', PleasePayeeAudit);

    PleasePayeeAudit.$inject = ['$resource'];

    function PleasePayeeAudit ($resource) {
        var resourceUrl =  'api/please-payee-audits/:id';

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
