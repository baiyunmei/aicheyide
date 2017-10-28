(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('postpone-record', {
            parent: 'entity',
            url: '/postpone-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PostponeRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/postpone-record/postpone-records.html',
                    controller: 'PostponeRecordController',
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
        .state('postpone-record-detail', {
            parent: 'postpone-record',
            url: '/postpone-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PostponeRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/postpone-record/postpone-record-detail.html',
                    controller: 'PostponeRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'PostponeRecord', function($stateParams, PostponeRecord) {
                    return PostponeRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'postpone-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('postpone-record-detail.edit', {
            parent: 'postpone-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/postpone-record/postpone-record-dialog.html',
                    controller: 'PostponeRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PostponeRecord', function(PostponeRecord) {
                            return PostponeRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('postpone-record.new', {
            parent: 'postpone-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/postpone-record/postpone-record-dialog.html',
                    controller: 'PostponeRecordDialogController',
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
                                operationTime: null,
                                plateNumber: null,
                                driverName: null,
                                postponeData: null,
                                remark: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('postpone-record', null, { reload: 'postpone-record' });
                }, function() {
                    $state.go('postpone-record');
                });
            }]
        })
        .state('postpone-record.edit', {
            parent: 'postpone-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/postpone-record/postpone-record-dialog.html',
                    controller: 'PostponeRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PostponeRecord', function(PostponeRecord) {
                            return PostponeRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('postpone-record', null, { reload: 'postpone-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('postpone-record.delete', {
            parent: 'postpone-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/postpone-record/postpone-record-delete-dialog.html',
                    controller: 'PostponeRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PostponeRecord', function(PostponeRecord) {
                            return PostponeRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('postpone-record', null, { reload: 'postpone-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
