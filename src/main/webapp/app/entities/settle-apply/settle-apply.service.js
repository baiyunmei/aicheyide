(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('SettleApply', SettleApply);

    SettleApply.$inject = ['$resource'];

    function SettleApply ($resource) {
        var resourceUrl =  'api/settle-applies/:id';

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
