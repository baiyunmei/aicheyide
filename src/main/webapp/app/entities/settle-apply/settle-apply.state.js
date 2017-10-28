(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('settle-apply', {
            parent: 'entity',
            url: '/settle-apply?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'SettleApplies'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/settle-apply/settle-applies.html',
                    controller: 'SettleApplyController',
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
        .state('settle-apply-detail', {
            parent: 'settle-apply',
            url: '/settle-apply/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'SettleApply'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/settle-apply/settle-apply-detail.html',
                    controller: 'SettleApplyDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'SettleApply', function($stateParams, SettleApply) {
                    return SettleApply.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'settle-apply',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('settle-apply-detail.edit', {
            parent: 'settle-apply-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/settle-apply/settle-apply-dialog.html',
                    controller: 'SettleApplyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SettleApply', function(SettleApply) {
                            return SettleApply.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('settle-apply.new', {
            parent: 'settle-apply',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/settle-apply/settle-apply-dialog.html',
                    controller: 'SettleApplyDialogController',
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
                                settleType: null,
                                receiptNumber: null,
                                receiptDate: null,
                                deductMarks: null,
                                fine: null,
                                pending: null,
                                remark: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('settle-apply', null, { reload: 'settle-apply' });
                }, function() {
                    $state.go('settle-apply');
                });
            }]
        })
        .state('settle-apply.edit', {
            parent: 'settle-apply',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/settle-apply/settle-apply-dialog.html',
                    controller: 'SettleApplyDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SettleApply', function(SettleApply) {
                            return SettleApply.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('settle-apply', null, { reload: 'settle-apply' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('settle-apply.delete', {
            parent: 'settle-apply',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/settle-apply/settle-apply-delete-dialog.html',
                    controller: 'SettleApplyDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['SettleApply', function(SettleApply) {
                            return SettleApply.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('settle-apply', null, { reload: 'settle-apply' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
