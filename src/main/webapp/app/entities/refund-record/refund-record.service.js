(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('RefundRecord', RefundRecord);

    RefundRecord.$inject = ['$resource'];

    function RefundRecord ($resource) {
        var resourceUrl =  'api/refund-records/:id';

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
