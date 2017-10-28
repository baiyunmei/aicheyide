(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('buy-vehicle-record', {
            parent: 'entity',
            url: '/buy-vehicle-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'BuyVehicleRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/buy-vehicle-record/buy-vehicle-records.html',
                    controller: 'BuyVehicleRecordController',
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
        .state('buy-vehicle-record-detail', {
            parent: 'buy-vehicle-record',
            url: '/buy-vehicle-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'BuyVehicleRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/buy-vehicle-record/buy-vehicle-record-detail.html',
                    controller: 'BuyVehicleRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'BuyVehicleRecord', function($stateParams, BuyVehicleRecord) {
                    return BuyVehicleRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'buy-vehicle-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('buy-vehicle-record-detail.edit', {
            parent: 'buy-vehicle-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/buy-vehicle-record/buy-vehicle-record-dialog.html',
                    controller: 'BuyVehicleRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BuyVehicleRecord', function(BuyVehicleRecord) {
                            return BuyVehicleRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('buy-vehicle-record.new', {
            parent: 'buy-vehicle-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/buy-vehicle-record/buy-vehicle-record-dialog.html',
                    controller: 'BuyVehicleRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                driverId: null,
                                vehicleId: null,
                                companyId: null,
                                receiptNumber: null,
                                buyVehicleDate: null,
                                plateNumber: null,
                                vehicleShelf: null,
                                engine: null,
                                downPayment: null,
                                cashPledge: null,
                                periods: null,
                                amount: null,
                                notPeriods: null,
                                notAmount: null,
                                postponeTime: null,
                                overdueTiem: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('buy-vehicle-record', null, { reload: 'buy-vehicle-record' });
                }, function() {
                    $state.go('buy-vehicle-record');
                });
            }]
        })
        .state('buy-vehicle-record.edit', {
            parent: 'buy-vehicle-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/buy-vehicle-record/buy-vehicle-record-dialog.html',
                    controller: 'BuyVehicleRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BuyVehicleRecord', function(BuyVehicleRecord) {
                            return BuyVehicleRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('buy-vehicle-record', null, { reload: 'buy-vehicle-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('buy-vehicle-record.delete', {
            parent: 'buy-vehicle-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/buy-vehicle-record/buy-vehicle-record-delete-dialog.html',
                    controller: 'BuyVehicleRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['BuyVehicleRecord', function(BuyVehicleRecord) {
                            return BuyVehicleRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('buy-vehicle-record', null, { reload: 'buy-vehicle-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
