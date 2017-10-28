(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('CommodityData', CommodityData);

    CommodityData.$inject = ['$resource'];

    function CommodityData ($resource) {
        var resourceUrl =  'api/commodity-data/:id';

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
