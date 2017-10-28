(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('data-collection', {
            parent: 'entity',
            url: '/data-collection?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DataCollections'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/data-collection/data-collections.html',
                    controller: 'DataCollectionController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
            }
        })
        .state('data-collection-detail', {
            parent: 'data-collection',
            url: '/data-collection/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DataCollection'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/data-collection/data-collection-detail.html',
                    controller: 'DataCollectionDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DataCollection', function($stateParams, DataCollection) {
                    return DataCollection.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'data-collection',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('data-collection-detail.edit', {
            parent: 'data-collection-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-collection/data-collection-dialog.html',
                    controller: 'DataCollectionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DataCollection', function(DataCollection) {
                            return DataCollection.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('data-collection.new', {
            parent: 'data-collection',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-collection/data-collection-dialog.html',
                    controller: 'DataCollectionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                driverId: null,
                                orderId: null,
                                data: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('data-collection', null, { reload: 'data-collection' });
                }, function() {
                    $state.go('data-collection');
                });
            }]
        })
        .state('data-collection.edit', {
            parent: 'data-collection',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-collection/data-collection-dialog.html',
                    controller: 'DataCollectionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DataCollection', function(DataCollection) {
                            return DataCollection.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('data-collection', null, { reload: 'data-collection' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('data-collection.delete', {
            parent: 'data-collection',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data-collection/data-collection-delete-dialog.html',
                    controller: 'DataCollectionDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DataCollection', function(DataCollection) {
                            return DataCollection.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('data-collection', null, { reload: 'data-collection' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
