(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('authorization-record', {
            parent: 'entity',
            url: '/authorization-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AuthorizationRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/authorization-record/authorization-records.html',
                    controller: 'AuthorizationRecordController',
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
        .state('authorization-record-detail', {
            parent: 'authorization-record',
            url: '/authorization-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AuthorizationRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/authorization-record/authorization-record-detail.html',
                    controller: 'AuthorizationRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'AuthorizationRecord', function($stateParams, AuthorizationRecord) {
                    return AuthorizationRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'authorization-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('authorization-record-detail.edit', {
            parent: 'authorization-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/authorization-record/authorization-record-dialog.html',
                    controller: 'AuthorizationRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AuthorizationRecord', function(AuthorizationRecord) {
                            return AuthorizationRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('authorization-record.new', {
            parent: 'authorization-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/authorization-record/authorization-record-dialog.html',
                    controller: 'AuthorizationRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                contractNumbering: null,
                                companyId: null,
                                purchaseDate: null,
                                authorizationStartDate: null,
                                authorizationFinishDate: null,
                                authorizationType: null,
                                remark: null,
                                authorizationId: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('authorization-record', null, { reload: 'authorization-record' });
                }, function() {
                    $state.go('authorization-record');
                });
            }]
        })
        .state('authorization-record.edit', {
            parent: 'authorization-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/authorization-record/authorization-record-dialog.html',
                    controller: 'AuthorizationRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AuthorizationRecord', function(AuthorizationRecord) {
                            return AuthorizationRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('authorization-record', null, { reload: 'authorization-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('authorization-record.delete', {
            parent: 'authorization-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/authorization-record/authorization-record-delete-dialog.html',
                    controller: 'AuthorizationRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['AuthorizationRecord', function(AuthorizationRecord) {
                            return AuthorizationRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('authorization-record', null, { reload: 'authorization-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
