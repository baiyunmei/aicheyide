(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('OnBrand', OnBrand);

    OnBrand.$inject = ['$resource'];

    function OnBrand ($resource) {
        var resourceUrl =  'api/on-brands/:id';

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
