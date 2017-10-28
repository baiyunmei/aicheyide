(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('MarketingPlan', MarketingPlan);

    MarketingPlan.$inject = ['$resource'];

    function MarketingPlan ($resource) {
        var resourceUrl =  'api/marketing-plans/:id';

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
