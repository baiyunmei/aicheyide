(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('GPSRefit', GPSRefit);

    GPSRefit.$inject = ['$resource'];

    function GPSRefit ($resource) {
        var resourceUrl =  'api/g-ps-refits/:id';

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
