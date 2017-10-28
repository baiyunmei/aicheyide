(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('ValidateRecord', ValidateRecord);

    ValidateRecord.$inject = ['$resource'];

    function ValidateRecord ($resource) {
        var resourceUrl =  'api/validate-records/:id';

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
