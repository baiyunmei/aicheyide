(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('commodity-data', {
            parent: 'entity',
            url: '/commodity-data?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'CommodityData'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/commodity-data/commodity-data.html',
                    controller: 'CommodityDataController',
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
        .state('commodity-data-detail', {
            parent: 'commodity-data',
            url: '/commodity-data/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'CommodityData'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/commodity-data/commodity-data-detail.html',
                    controller: 'CommodityDataDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'CommodityData', function($stateParams, CommodityData) {
                    return CommodityData.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'commodity-data',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('commodity-data-detail.edit', {
            parent: 'commodity-data-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/commodity-data/commodity-data-dialog.html',
                    controller: 'CommodityDataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CommodityData', function(CommodityData) {
                            return CommodityData.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('commodity-data.new', {
            parent: 'commodity-data',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/commodity-data/commodity-data-dialog.html',
                    controller: 'CommodityDataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                companyId: null,
                                mnemonicCode: null,
                                brand: null,
                                salesName: null,
                                colour: null,
                                size: null,
                                commodityType: null,
                                normalPrice: null,
                                remark: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('commodity-data', null, { reload: 'commodity-data' });
                }, function() {
                    $state.go('commodity-data');
                });
            }]
        })
        .state('commodity-data.edit', {
            parent: 'commodity-data',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/commodity-data/commodity-data-dialog.html',
                    controller: 'CommodityDataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CommodityData', function(CommodityData) {
                            return CommodityData.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('commodity-data', null, { reload: 'commodity-data' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('commodity-data.delete', {
            parent: 'commodity-data',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/commodity-data/commodity-data-delete-dialog.html',
                    controller: 'CommodityDataDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CommodityData', function(CommodityData) {
                            return CommodityData.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('commodity-data', null, { reload: 'commodity-data' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
