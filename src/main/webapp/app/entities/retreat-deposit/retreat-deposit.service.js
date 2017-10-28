(function() {
    'use strict';
    angular
        .module('aicheyideApp')
        .factory('RetreatDeposit', RetreatDeposit);

    RetreatDeposit.$inject = ['$resource'];

    function RetreatDeposit ($resource) {
        var resourceUrl =  'api/retreat-deposits/:id';

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
