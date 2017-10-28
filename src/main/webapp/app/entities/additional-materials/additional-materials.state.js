(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('additional-materials', {
            parent: 'entity',
            url: '/additional-materials?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AdditionalMaterials'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/additional-materials/additional-materials.html',
                    controller: 'AdditionalMaterialsController',
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
        .state('additional-materials-detail', {
            parent: 'additional-materials',
            url: '/additional-materials/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AdditionalMaterials'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/additional-materials/additional-materials-detail.html',
                    controller: 'AdditionalMaterialsDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'AdditionalMaterials', function($stateParams, AdditionalMaterials) {
                    return AdditionalMaterials.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'additional-materials',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('additional-materials-detail.edit', {
            parent: 'additional-materials-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/additional-materials/additional-materials-dialog.html',
                    controller: 'AdditionalMaterialsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AdditionalMaterials', function(AdditionalMaterials) {
                            return AdditionalMaterials.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('additional-materials.new', {
            parent: 'additional-materials',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/additional-materials/additional-materials-dialog.html',
                    controller: 'AdditionalMaterialsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                orderId: null,
                                registered: null,
                                marriage: null,
                                site: null,
                                communicate: null,
                                agreement: null,
                                bankCard: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('additional-materials', null, { reload: 'additional-materials' });
                }, function() {
                    $state.go('additional-materials');
                });
            }]
        })
        .state('additional-materials.edit', {
            parent: 'additional-materials',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/additional-materials/additional-materials-dialog.html',
                    controller: 'AdditionalMaterialsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AdditionalMaterials', function(AdditionalMaterials) {
                            return AdditionalMaterials.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('additional-materials', null, { reload: 'additional-materials' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('additional-materials.delete', {
            parent: 'additional-materials',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/additional-materials/additional-materials-delete-dialog.html',
                    controller: 'AdditionalMaterialsDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['AdditionalMaterials', function(AdditionalMaterials) {
                            return AdditionalMaterials.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('additional-materials', null, { reload: 'additional-materials' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
