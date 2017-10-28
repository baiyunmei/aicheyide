(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('GasRefit', GasRefit);

    GasRefit.$inject = ['$resource'];

    function GasRefit ($resource) {
        var resourceUrl =  'api/gas-refits/:id';

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
