(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('CompanyMessage', CompanyMessage);

    CompanyMessage.$inject = ['$resource'];

    function CompanyMessage ($resource) {
        var resourceUrl =  'api/company-messages/:id';

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
