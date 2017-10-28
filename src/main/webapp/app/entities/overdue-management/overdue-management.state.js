(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('overdue-management', {
            parent: 'entity',
            url: '/overdue-management?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'OverdueManagements'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/overdue-management/overdue-managements.html',
                    controller: 'OverdueManagementController',
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
        .state('overdue-management-detail', {
            parent: 'overdue-management',
            url: '/overdue-management/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'OverdueManagement'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/overdue-management/overdue-management-detail.html',
                    controller: 'OverdueManagementDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'OverdueManagement', function($stateParams, OverdueManagement) {
                    return OverdueManagement.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'overdue-management',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('overdue-management-detail.edit', {
            parent: 'overdue-management-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/overdue-management/overdue-management-dialog.html',
                    controller: 'OverdueManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OverdueManagement', function(OverdueManagement) {
                            return OverdueManagement.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('overdue-management.new', {
            parent: 'overdue-management',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/overdue-management/overdue-management-dialog.html',
                    controller: 'OverdueManagementDialogController',
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
                                orderId: null,
                                repaymentTime: null,
                                money: null,
                                overdueData: null,
                                overdueInterest: null,
                                nextMoneyData: null,
                                postponeData: null,
                                informVehicle: null,
                                recycleVehicle: null,
                                remark: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('overdue-management', null, { reload: 'overdue-management' });
                }, function() {
                    $state.go('overdue-management');
                });
            }]
        })
        .state('overdue-management.edit', {
            parent: 'overdue-management',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/overdue-management/overdue-management-dialog.html',
                    controller: 'OverdueManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OverdueManagement', function(OverdueManagement) {
                            return OverdueManagement.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('overdue-management', null, { reload: 'overdue-management' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('overdue-management.delete', {
            parent: 'overdue-management',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/overdue-management/overdue-management-delete-dialog.html',
                    controller: 'OverdueManagementDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['OverdueManagement', function(OverdueManagement) {
                            return OverdueManagement.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('overdue-management', null, { reload: 'overdue-management' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
