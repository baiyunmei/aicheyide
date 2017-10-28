(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('refund-record', {
            parent: 'entity',
            url: '/refund-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RefundRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/refund-record/refund-records.html',
                    controller: 'RefundRecordController',
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
        .state('refund-record-detail', {
            parent: 'refund-record',
            url: '/refund-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RefundRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/refund-record/refund-record-detail.html',
                    controller: 'RefundRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'RefundRecord', function($stateParams, RefundRecord) {
                    return RefundRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'refund-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('refund-record-detail.edit', {
            parent: 'refund-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/refund-record/refund-record-dialog.html',
                    controller: 'RefundRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RefundRecord', function(RefundRecord) {
                            return RefundRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('refund-record.new', {
            parent: 'refund-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/refund-record/refund-record-dialog.html',
                    controller: 'RefundRecordDialogController',
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
                                refundTime: null,
                                money: null,
                                source: null,
                                depositStatus: null,
                                remark: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('refund-record', null, { reload: 'refund-record' });
                }, function() {
                    $state.go('refund-record');
                });
            }]
        })
        .state('refund-record.edit', {
            parent: 'refund-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/refund-record/refund-record-dialog.html',
                    controller: 'RefundRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RefundRecord', function(RefundRecord) {
                            return RefundRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('refund-record', null, { reload: 'refund-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('refund-record.delete', {
            parent: 'refund-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/refund-record/refund-record-delete-dialog.html',
                    controller: 'RefundRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RefundRecord', function(RefundRecord) {
                            return RefundRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('refund-record', null, { reload: 'refund-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
