(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('MaintainRecord', MaintainRecord);

    MaintainRecord.$inject = ['$resource'];

    function MaintainRecord ($resource) {
        var resourceUrl =  'api/maintain-records/:id';

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
