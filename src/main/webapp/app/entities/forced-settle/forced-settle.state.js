(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('forced-settle', {
            parent: 'entity',
            url: '/forced-settle?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ForcedSettles'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/forced-settle/forced-settles.html',
                    controller: 'ForcedSettleController',
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
        .state('forced-settle-detail', {
            parent: 'forced-settle',
            url: '/forced-settle/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ForcedSettle'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/forced-settle/forced-settle-detail.html',
                    controller: 'ForcedSettleDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'ForcedSettle', function($stateParams, ForcedSettle) {
                    return ForcedSettle.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'forced-settle',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('forced-settle-detail.edit', {
            parent: 'forced-settle-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/forced-settle/forced-settle-dialog.html',
                    controller: 'ForcedSettleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ForcedSettle', function(ForcedSettle) {
                            return ForcedSettle.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('forced-settle.new', {
            parent: 'forced-settle',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/forced-settle/forced-settle-dialog.html',
                    controller: 'ForcedSettleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                driverId: null,
                                vehicleId: null,
                                companyId: null,
                                plateNumber: null,
                                driverName: null,
                                periods: null,
                                sumMoney: null,
                                notPeriods: null,
                                notMoney: null,
                                settleType: null,
                                receiptNumber: null,
                                receiptDate: null,
                                whetherRecycle: null,
                                remark: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('forced-settle', null, { reload: 'forced-settle' });
                }, function() {
                    $state.go('forced-settle');
                });
            }]
        })
        .state('forced-settle.edit', {
            parent: 'forced-settle',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/forced-settle/forced-settle-dialog.html',
                    controller: 'ForcedSettleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ForcedSettle', function(ForcedSettle) {
                            return ForcedSettle.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('forced-settle', null, { reload: 'forced-settle' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('forced-settle.delete', {
            parent: 'forced-settle',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/forced-settle/forced-settle-delete-dialog.html',
                    controller: 'ForcedSettleDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ForcedSettle', function(ForcedSettle) {
                            return ForcedSettle.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('forced-settle', null, { reload: 'forced-settle' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
