(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('monthly-management', {
            parent: 'entity',
            url: '/monthly-management?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'MonthlyManagements'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/monthly-management/monthly-managements.html',
                    controller: 'MonthlyManagementController',
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
        .state('monthly-management-detail', {
            parent: 'monthly-management',
            url: '/monthly-management/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'MonthlyManagement'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/monthly-management/monthly-management-detail.html',
                    controller: 'MonthlyManagementDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'MonthlyManagement', function($stateParams, MonthlyManagement) {
                    return MonthlyManagement.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'monthly-management',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('monthly-management-detail.edit', {
            parent: 'monthly-management-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/monthly-management/monthly-management-dialog.html',
                    controller: 'MonthlyManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MonthlyManagement', function(MonthlyManagement) {
                            return MonthlyManagement.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('monthly-management.new', {
            parent: 'monthly-management',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/monthly-management/monthly-management-dialog.html',
                    controller: 'MonthlyManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                driverId: null,
                                orderId: null,
                                companyId: null,
                                driverName: null,
                                bankAccount: null,
                                repaymentTime: null,
                                money: null,
                                overdue: null,
                                periods: null,
                                nextMoney: null,
                                nextDate: null,
                                periodsStatus: null,
                                remark: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('monthly-management', null, { reload: 'monthly-management' });
                }, function() {
                    $state.go('monthly-management');
                });
            }]
        })
        .state('monthly-management.edit', {
            parent: 'monthly-management',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/monthly-management/monthly-management-dialog.html',
                    controller: 'MonthlyManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MonthlyManagement', function(MonthlyManagement) {
                            return MonthlyManagement.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('monthly-management', null, { reload: 'monthly-management' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('monthly-management.delete', {
            parent: 'monthly-management',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/monthly-management/monthly-management-delete-dialog.html',
                    controller: 'MonthlyManagementDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['MonthlyManagement', function(MonthlyManagement) {
                            return MonthlyManagement.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('monthly-management', null, { reload: 'monthly-management' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
