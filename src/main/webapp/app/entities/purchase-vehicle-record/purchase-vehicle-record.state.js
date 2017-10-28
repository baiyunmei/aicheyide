(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('purchase-vehicle-record', {
            parent: 'entity',
            url: '/purchase-vehicle-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PurchaseVehicleRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/purchase-vehicle-record/purchase-vehicle-records.html',
                    controller: 'PurchaseVehicleRecordController',
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
        .state('purchase-vehicle-record-detail', {
            parent: 'purchase-vehicle-record',
            url: '/purchase-vehicle-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PurchaseVehicleRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/purchase-vehicle-record/purchase-vehicle-record-detail.html',
                    controller: 'PurchaseVehicleRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'PurchaseVehicleRecord', function($stateParams, PurchaseVehicleRecord) {
                    return PurchaseVehicleRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'purchase-vehicle-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('purchase-vehicle-record-detail.edit', {
            parent: 'purchase-vehicle-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/purchase-vehicle-record/purchase-vehicle-record-dialog.html',
                    controller: 'PurchaseVehicleRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PurchaseVehicleRecord', function(PurchaseVehicleRecord) {
                            return PurchaseVehicleRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('purchase-vehicle-record.new', {
            parent: 'purchase-vehicle-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/purchase-vehicle-record/purchase-vehicle-record-dialog.html',
                    controller: 'PurchaseVehicleRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('purchase-vehicle-record', null, { reload: 'purchase-vehicle-record' });
                }, function() {
                    $state.go('purchase-vehicle-record');
                });
            }]
        })
        .state('purchase-vehicle-record.edit', {
            parent: 'purchase-vehicle-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/purchase-vehicle-record/purchase-vehicle-record-dialog.html',
                    controller: 'PurchaseVehicleRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PurchaseVehicleRecord', function(PurchaseVehicleRecord) {
                            return PurchaseVehicleRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('purchase-vehicle-record', null, { reload: 'purchase-vehicle-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('purchase-vehicle-record.delete', {
            parent: 'purchase-vehicle-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/purchase-vehicle-record/purchase-vehicle-record-delete-dialog.html',
                    controller: 'PurchaseVehicleRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PurchaseVehicleRecord', function(PurchaseVehicleRecord) {
                            return PurchaseVehicleRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('purchase-vehicle-record', null, { reload: 'purchase-vehicle-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
