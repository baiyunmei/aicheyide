(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('log-record', {
            parent: 'entity',
            url: '/log-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'LogRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/log-record/log-records.html',
                    controller: 'LogRecordController',
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
        .state('log-record-detail', {
            parent: 'log-record',
            url: '/log-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'LogRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/log-record/log-record-detail.html',
                    controller: 'LogRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'LogRecord', function($stateParams, LogRecord) {
                    return LogRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'log-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('log-record-detail.edit', {
            parent: 'log-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/log-record/log-record-dialog.html',
                    controller: 'LogRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LogRecord', function(LogRecord) {
                            return LogRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('log-record.new', {
            parent: 'log-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/log-record/log-record-dialog.html',
                    controller: 'LogRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                operatingType: null,
                                operator: null,
                                companyId: null,
                                departmentId: null,
                                operatorId: null,
                                ip: null,
                                role: null,
                                operatingDate: null,
                                remark: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('log-record', null, { reload: 'log-record' });
                }, function() {
                    $state.go('log-record');
                });
            }]
        })
        .state('log-record.edit', {
            parent: 'log-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/log-record/log-record-dialog.html',
                    controller: 'LogRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LogRecord', function(LogRecord) {
                            return LogRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('log-record', null, { reload: 'log-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('log-record.delete', {
            parent: 'log-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/log-record/log-record-delete-dialog.html',
                    controller: 'LogRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['LogRecord', function(LogRecord) {
                            return LogRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('log-record', null, { reload: 'log-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
