(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('contract-margin-record', {
            parent: 'entity',
            url: '/contract-margin-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ContractMarginRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/contract-margin-record/contract-margin-records.html',
                    controller: 'ContractMarginRecordController',
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
        .state('contract-margin-record-detail', {
            parent: 'contract-margin-record',
            url: '/contract-margin-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ContractMarginRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/contract-margin-record/contract-margin-record-detail.html',
                    controller: 'ContractMarginRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'ContractMarginRecord', function($stateParams, ContractMarginRecord) {
                    return ContractMarginRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'contract-margin-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('contract-margin-record-detail.edit', {
            parent: 'contract-margin-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contract-margin-record/contract-margin-record-dialog.html',
                    controller: 'ContractMarginRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ContractMarginRecord', function(ContractMarginRecord) {
                            return ContractMarginRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('contract-margin-record.new', {
            parent: 'contract-margin-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contract-margin-record/contract-margin-record-dialog.html',
                    controller: 'ContractMarginRecordDialogController',
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
                                paymentTime: null,
                                paymentWay: null,
                                paymentMoney: null,
                                paymentStatus: null,
                                remark: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('contract-margin-record', null, { reload: 'contract-margin-record' });
                }, function() {
                    $state.go('contract-margin-record');
                });
            }]
        })
        .state('contract-margin-record.edit', {
            parent: 'contract-margin-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contract-margin-record/contract-margin-record-dialog.html',
                    controller: 'ContractMarginRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ContractMarginRecord', function(ContractMarginRecord) {
                            return ContractMarginRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('contract-margin-record', null, { reload: 'contract-margin-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('contract-margin-record.delete', {
            parent: 'contract-margin-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contract-margin-record/contract-margin-record-delete-dialog.html',
                    controller: 'ContractMarginRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ContractMarginRecord', function(ContractMarginRecord) {
                            return ContractMarginRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('contract-margin-record', null, { reload: 'contract-margin-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
