(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('OutDangerRecord', OutDangerRecord);

    OutDangerRecord.$inject = ['$resource'];

    function OutDangerRecord ($resource) {
        var resourceUrl =  'api/out-danger-records/:id';

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
