(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('out-danger-record', {
            parent: 'entity',
            url: '/out-danger-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'OutDangerRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/out-danger-record/out-danger-records.html',
                    controller: 'OutDangerRecordController',
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
        .state('out-danger-record-detail', {
            parent: 'out-danger-record',
            url: '/out-danger-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'OutDangerRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/out-danger-record/out-danger-record-detail.html',
                    controller: 'OutDangerRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'OutDangerRecord', function($stateParams, OutDangerRecord) {
                    return OutDangerRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'out-danger-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('out-danger-record-detail.edit', {
            parent: 'out-danger-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/out-danger-record/out-danger-record-dialog.html',
                    controller: 'OutDangerRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OutDangerRecord', function(OutDangerRecord) {
                            return OutDangerRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('out-danger-record.new', {
            parent: 'out-danger-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/out-danger-record/out-danger-record-dialog.html',
                    controller: 'OutDangerRecordDialogController',
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
                                comeTime: null,
                                responsibleParty: null,
                                oneMoney: null,
                                threeMoney: null,
                                repairFactory: null,
                                remark: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('out-danger-record', null, { reload: 'out-danger-record' });
                }, function() {
                    $state.go('out-danger-record');
                });
            }]
        })
        .state('out-danger-record.edit', {
            parent: 'out-danger-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/out-danger-record/out-danger-record-dialog.html',
                    controller: 'OutDangerRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OutDangerRecord', function(OutDangerRecord) {
                            return OutDangerRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('out-danger-record', null, { reload: 'out-danger-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('out-danger-record.delete', {
            parent: 'out-danger-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/out-danger-record/out-danger-record-delete-dialog.html',
                    controller: 'OutDangerRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['OutDangerRecord', function(OutDangerRecord) {
                            return OutDangerRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('out-danger-record', null, { reload: 'out-danger-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
