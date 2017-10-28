(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('AdditionalMaterials', AdditionalMaterials);

    AdditionalMaterials.$inject = ['$resource'];

    function AdditionalMaterials ($resource) {
        var resourceUrl =  'api/additional-materials/:id';

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
