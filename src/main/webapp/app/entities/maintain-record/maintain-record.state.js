(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('maintain-record', {
            parent: 'entity',
            url: '/maintain-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'MaintainRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/maintain-record/maintain-records.html',
                    controller: 'MaintainRecordController',
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
        .state('maintain-record-detail', {
            parent: 'maintain-record',
            url: '/maintain-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'MaintainRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/maintain-record/maintain-record-detail.html',
                    controller: 'MaintainRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'MaintainRecord', function($stateParams, MaintainRecord) {
                    return MaintainRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'maintain-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('maintain-record-detail.edit', {
            parent: 'maintain-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/maintain-record/maintain-record-dialog.html',
                    controller: 'MaintainRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MaintainRecord', function(MaintainRecord) {
                            return MaintainRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('maintain-record.new', {
            parent: 'maintain-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/maintain-record/maintain-record-dialog.html',
                    controller: 'MaintainRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                driverId: null,
                                vehicleId: null,
                                companyId: null,
                                maintainNumber: null,
                                plateNumber: null,
                                driverName: null,
                                maintainTime: null,
                                money: null,
                                repairFactory: null,
                                leaveFactoryTime: null,
                                maintainSky: null,
                                remark: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('maintain-record', null, { reload: 'maintain-record' });
                }, function() {
                    $state.go('maintain-record');
                });
            }]
        })
        .state('maintain-record.edit', {
            parent: 'maintain-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/maintain-record/maintain-record-dialog.html',
                    controller: 'MaintainRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MaintainRecord', function(MaintainRecord) {
                            return MaintainRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('maintain-record', null, { reload: 'maintain-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('maintain-record.delete', {
            parent: 'maintain-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/maintain-record/maintain-record-delete-dialog.html',
                    controller: 'MaintainRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['MaintainRecord', function(MaintainRecord) {
                            return MaintainRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('maintain-record', null, { reload: 'maintain-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
