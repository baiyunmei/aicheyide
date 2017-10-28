(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('order-from', {
            parent: 'entity',
            url: '/order-from?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'OrderFroms'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/order-from/order-froms.html',
                    controller: 'OrderFromController',
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
        .state('order-from-detail', {
            parent: 'order-from',
            url: '/order-from/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'OrderFrom'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/order-from/order-from-detail.html',
                    controller: 'OrderFromDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'OrderFrom', function($stateParams, OrderFrom) {
                    return OrderFrom.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'order-from',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('order-from-detail.edit', {
            parent: 'order-from-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order-from/order-from-dialog.html',
                    controller: 'OrderFromDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OrderFrom', function(OrderFrom) {
                            return OrderFrom.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('order-from.new', {
            parent: 'order-from',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order-from/order-from-dialog.html',
                    controller: 'OrderFromDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                companyId: null,
                                marketId: null,
                                driverId: null,
                                productType: null,
                                fuelType: null,
                                brandName: null,
                                type: null,
                                versions: null,
                                year: null,
                                refit: null,
                                salesPlan: null,
                                salesPlanId: null,
                                downPayment: null,
                                monthly: null,
                                periods: null,
                                cashPledge: null,
                                rent: null,
                                referralsIdNumber: null,
                                phone: null,
                                name: null,
                                auditStatus: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('order-from', null, { reload: 'order-from' });
                }, function() {
                    $state.go('order-from');
                });
            }]
        })
        .state('order-from.edit', {
            parent: 'order-from',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order-from/order-from-dialog.html',
                    controller: 'OrderFromDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OrderFrom', function(OrderFrom) {
                            return OrderFrom.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('order-from', null, { reload: 'order-from' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('order-from.delete', {
            parent: 'order-from',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order-from/order-from-delete-dialog.html',
                    controller: 'OrderFromDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['OrderFrom', function(OrderFrom) {
                            return OrderFrom.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('order-from', null, { reload: 'order-from' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
