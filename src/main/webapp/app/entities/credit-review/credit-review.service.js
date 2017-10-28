(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('CreditReview', CreditReview);

    CreditReview.$inject = ['$resource'];

    function CreditReview ($resource) {
        var resourceUrl =  'api/credit-reviews/:id';

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
