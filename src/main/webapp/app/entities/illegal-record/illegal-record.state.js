(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('illegal-record', {
            parent: 'entity',
            url: '/illegal-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'IllegalRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/illegal-record/illegal-records.html',
                    controller: 'IllegalRecordController',
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
        .state('illegal-record-detail', {
            parent: 'illegal-record',
            url: '/illegal-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'IllegalRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/illegal-record/illegal-record-detail.html',
                    controller: 'IllegalRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'IllegalRecord', function($stateParams, IllegalRecord) {
                    return IllegalRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'illegal-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('illegal-record-detail.edit', {
            parent: 'illegal-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/illegal-record/illegal-record-dialog.html',
                    controller: 'IllegalRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['IllegalRecord', function(IllegalRecord) {
                            return IllegalRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('illegal-record.new', {
            parent: 'illegal-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/illegal-record/illegal-record-dialog.html',
                    controller: 'IllegalRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                driverId: null,
                                vehicleId: null,
                                companyId: null,
                                plateNumber: null,
                                illegalDate: null,
                                illegalSite: null,
                                detail: null,
                                illegalMoney: null,
                                illegalDeduct: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('illegal-record', null, { reload: 'illegal-record' });
                }, function() {
                    $state.go('illegal-record');
                });
            }]
        })
        .state('illegal-record.edit', {
            parent: 'illegal-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/illegal-record/illegal-record-dialog.html',
                    controller: 'IllegalRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['IllegalRecord', function(IllegalRecord) {
                            return IllegalRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('illegal-record', null, { reload: 'illegal-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('illegal-record.delete', {
            parent: 'illegal-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/illegal-record/illegal-record-delete-dialog.html',
                    controller: 'IllegalRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['IllegalRecord', function(IllegalRecord) {
                            return IllegalRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('illegal-record', null, { reload: 'illegal-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
