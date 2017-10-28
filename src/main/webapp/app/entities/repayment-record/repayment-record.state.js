(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('repayment-record', {
            parent: 'entity',
            url: '/repayment-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RepaymentRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/repayment-record/repayment-records.html',
                    controller: 'RepaymentRecordController',
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
        .state('repayment-record-detail', {
            parent: 'repayment-record',
            url: '/repayment-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RepaymentRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/repayment-record/repayment-record-detail.html',
                    controller: 'RepaymentRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'RepaymentRecord', function($stateParams, RepaymentRecord) {
                    return RepaymentRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'repayment-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('repayment-record-detail.edit', {
            parent: 'repayment-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/repayment-record/repayment-record-dialog.html',
                    controller: 'RepaymentRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RepaymentRecord', function(RepaymentRecord) {
                            return RepaymentRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('repayment-record.new', {
            parent: 'repayment-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/repayment-record/repayment-record-dialog.html',
                    controller: 'RepaymentRecordDialogController',
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
                                repaymentTime: null,
                                money: null,
                                overdue: null,
                                periods: null,
                                nextMoney: null,
                                nextDate: null,
                                remark: null,
                                operationTime: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('repayment-record', null, { reload: 'repayment-record' });
                }, function() {
                    $state.go('repayment-record');
                });
            }]
        })
        .state('repayment-record.edit', {
            parent: 'repayment-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/repayment-record/repayment-record-dialog.html',
                    controller: 'RepaymentRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RepaymentRecord', function(RepaymentRecord) {
                            return RepaymentRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('repayment-record', null, { reload: 'repayment-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('repayment-record.delete', {
            parent: 'repayment-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/repayment-record/repayment-record-delete-dialog.html',
                    controller: 'RepaymentRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RepaymentRecord', function(RepaymentRecord) {
                            return RepaymentRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('repayment-record', null, { reload: 'repayment-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
