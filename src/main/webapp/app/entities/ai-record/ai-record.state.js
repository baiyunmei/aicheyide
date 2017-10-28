(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('ai-record', {
            parent: 'entity',
            url: '/ai-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AiRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ai-record/ai-records.html',
                    controller: 'AiRecordController',
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
        .state('ai-record-detail', {
            parent: 'ai-record',
            url: '/ai-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'AiRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ai-record/ai-record-detail.html',
                    controller: 'AiRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'AiRecord', function($stateParams, AiRecord) {
                    return AiRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'ai-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('ai-record-detail.edit', {
            parent: 'ai-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ai-record/ai-record-dialog.html',
                    controller: 'AiRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AiRecord', function(AiRecord) {
                            return AiRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ai-record.new', {
            parent: 'ai-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ai-record/ai-record-dialog.html',
                    controller: 'AiRecordDialogController',
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
                                aiDate: null,
                                nextAiDate: null,
                                aiConductor: null,
                                money: null,
                                remark: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('ai-record', null, { reload: 'ai-record' });
                }, function() {
                    $state.go('ai-record');
                });
            }]
        })
        .state('ai-record.edit', {
            parent: 'ai-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ai-record/ai-record-dialog.html',
                    controller: 'AiRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['AiRecord', function(AiRecord) {
                            return AiRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ai-record', null, { reload: 'ai-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ai-record.delete', {
            parent: 'ai-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ai-record/ai-record-delete-dialog.html',
                    controller: 'AiRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['AiRecord', function(AiRecord) {
                            return AiRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ai-record', null, { reload: 'ai-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
