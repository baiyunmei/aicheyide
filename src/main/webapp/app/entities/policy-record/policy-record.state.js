(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('policy-record', {
            parent: 'entity',
            url: '/policy-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PolicyRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/policy-record/policy-records.html',
                    controller: 'PolicyRecordController',
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
        .state('policy-record-detail', {
            parent: 'policy-record',
            url: '/policy-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PolicyRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/policy-record/policy-record-detail.html',
                    controller: 'PolicyRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'PolicyRecord', function($stateParams, PolicyRecord) {
                    return PolicyRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'policy-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('policy-record-detail.edit', {
            parent: 'policy-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/policy-record/policy-record-dialog.html',
                    controller: 'PolicyRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PolicyRecord', function(PolicyRecord) {
                            return PolicyRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('policy-record.new', {
            parent: 'policy-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/policy-record/policy-record-dialog.html',
                    controller: 'PolicyRecordDialogController',
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
                                viBeginDate: null,
                                viFinishDate: null,
                                ciBeginDate: null,
                                ciFinishDate: null,
                                remark: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('policy-record', null, { reload: 'policy-record' });
                }, function() {
                    $state.go('policy-record');
                });
            }]
        })
        .state('policy-record.edit', {
            parent: 'policy-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/policy-record/policy-record-dialog.html',
                    controller: 'PolicyRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PolicyRecord', function(PolicyRecord) {
                            return PolicyRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('policy-record', null, { reload: 'policy-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('policy-record.delete', {
            parent: 'policy-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/policy-record/policy-record-delete-dialog.html',
                    controller: 'PolicyRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PolicyRecord', function(PolicyRecord) {
                            return PolicyRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('policy-record', null, { reload: 'policy-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
