(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('RepaymentRecord', RepaymentRecord);

    RepaymentRecord.$inject = ['$resource'];

    function RepaymentRecord ($resource) {
        var resourceUrl =  'api/repayment-records/:id';

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
