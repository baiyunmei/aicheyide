(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('DownPayment', DownPayment);

    DownPayment.$inject = ['$resource'];

    function DownPayment ($resource) {
        var resourceUrl =  'api/down-payments/:id';

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
