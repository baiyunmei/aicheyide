(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('on-brand', {
            parent: 'entity',
            url: '/on-brand?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'OnBrands'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/on-brand/on-brands.html',
                    controller: 'OnBrandController',
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
        .state('on-brand-detail', {
            parent: 'on-brand',
            url: '/on-brand/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'OnBrand'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/on-brand/on-brand-detail.html',
                    controller: 'OnBrandDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'OnBrand', function($stateParams, OnBrand) {
                    return OnBrand.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'on-brand',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('on-brand-detail.edit', {
            parent: 'on-brand-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/on-brand/on-brand-dialog.html',
                    controller: 'OnBrandDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OnBrand', function(OnBrand) {
                            return OnBrand.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('on-brand.new', {
            parent: 'on-brand',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/on-brand/on-brand-dialog.html',
                    controller: 'OnBrandDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                vehicleId: null,
                                gpsdevice: null,
                                remark: null,
                                gpsInstall: null,
                                installTime: null,
                                founder: null,
                                founderTime: null,
                                modifier: null,
                                modifierTime: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('on-brand', null, { reload: 'on-brand' });
                }, function() {
                    $state.go('on-brand');
                });
            }]
        })
        .state('on-brand.edit', {
            parent: 'on-brand',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/on-brand/on-brand-dialog.html',
                    controller: 'OnBrandDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OnBrand', function(OnBrand) {
                            return OnBrand.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('on-brand', null, { reload: 'on-brand' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('on-brand.delete', {
            parent: 'on-brand',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/on-brand/on-brand-delete-dialog.html',
                    controller: 'OnBrandDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['OnBrand', function(OnBrand) {
                            return OnBrand.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('on-brand', null, { reload: 'on-brand' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
