(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rental-management', {
            parent: 'entity',
            url: '/rental-management?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RentalManagements'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rental-management/rental-managements.html',
                    controller: 'RentalManagementController',
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
        .state('rental-management-detail', {
            parent: 'rental-management',
            url: '/rental-management/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RentalManagement'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rental-management/rental-management-detail.html',
                    controller: 'RentalManagementDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'RentalManagement', function($stateParams, RentalManagement) {
                    return RentalManagement.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'rental-management',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('rental-management-detail.edit', {
            parent: 'rental-management-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rental-management/rental-management-dialog.html',
                    controller: 'RentalManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RentalManagement', function(RentalManagement) {
                            return RentalManagement.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rental-management.new', {
            parent: 'rental-management',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rental-management/rental-management-dialog.html',
                    controller: 'RentalManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                driverId: null,
                                orderId: null,
                                companyId: null,
                                vehicleId: null,
                                plateNumber: null,
                                driverName: null,
                                rentTime: null,
                                money: null,
                                overdue: null,
                                remark: null,
                                receiptNumber: null,
                                receiptDate: null,
                                nextRentTime: null,
                                founder: null,
                                founderTime: null,
                                modifier: null,
                                modifierTime: null,
                                whetherGather: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('rental-management', null, { reload: 'rental-management' });
                }, function() {
                    $state.go('rental-management');
                });
            }]
        })
        .state('rental-management.edit', {
            parent: 'rental-management',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rental-management/rental-management-dialog.html',
                    controller: 'RentalManagementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RentalManagement', function(RentalManagement) {
                            return RentalManagement.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rental-management', null, { reload: 'rental-management' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rental-management.delete', {
            parent: 'rental-management',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rental-management/rental-management-delete-dialog.html',
                    controller: 'RentalManagementDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RentalManagement', function(RentalManagement) {
                            return RentalManagement.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rental-management', null, { reload: 'rental-management' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
