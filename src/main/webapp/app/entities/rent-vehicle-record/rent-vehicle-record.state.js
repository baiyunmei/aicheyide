(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('rent-vehicle-record', {
            parent: 'entity',
            url: '/rent-vehicle-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RentVehicleRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rent-vehicle-record/rent-vehicle-records.html',
                    controller: 'RentVehicleRecordController',
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
        .state('rent-vehicle-record-detail', {
            parent: 'rent-vehicle-record',
            url: '/rent-vehicle-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RentVehicleRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/rent-vehicle-record/rent-vehicle-record-detail.html',
                    controller: 'RentVehicleRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'RentVehicleRecord', function($stateParams, RentVehicleRecord) {
                    return RentVehicleRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'rent-vehicle-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('rent-vehicle-record-detail.edit', {
            parent: 'rent-vehicle-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rent-vehicle-record/rent-vehicle-record-dialog.html',
                    controller: 'RentVehicleRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RentVehicleRecord', function(RentVehicleRecord) {
                            return RentVehicleRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rent-vehicle-record.new', {
            parent: 'rent-vehicle-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rent-vehicle-record/rent-vehicle-record-dialog.html',
                    controller: 'RentVehicleRecordDialogController',
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
                                plateNumber: null,
                                driverName: null,
                                phone: null,
                                contractStartDate: null,
                                contractEndDate: null,
                                monthlyRent: null,
                                cashPledge: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('rent-vehicle-record', null, { reload: 'rent-vehicle-record' });
                }, function() {
                    $state.go('rent-vehicle-record');
                });
            }]
        })
        .state('rent-vehicle-record.edit', {
            parent: 'rent-vehicle-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rent-vehicle-record/rent-vehicle-record-dialog.html',
                    controller: 'RentVehicleRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RentVehicleRecord', function(RentVehicleRecord) {
                            return RentVehicleRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rent-vehicle-record', null, { reload: 'rent-vehicle-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rent-vehicle-record.delete', {
            parent: 'rent-vehicle-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/rent-vehicle-record/rent-vehicle-record-delete-dialog.html',
                    controller: 'RentVehicleRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RentVehicleRecord', function(RentVehicleRecord) {
                            return RentVehicleRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('rent-vehicle-record', null, { reload: 'rent-vehicle-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
