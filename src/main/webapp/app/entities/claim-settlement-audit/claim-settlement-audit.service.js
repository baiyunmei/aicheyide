(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('ClaimSettlementAudit', ClaimSettlementAudit);

    ClaimSettlementAudit.$inject = ['$resource'];

    function ClaimSettlementAudit ($resource) {
        var resourceUrl =  'api/claim-settlement-audits/:id';

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
