(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('supplier-record', {
            parent: 'entity',
            url: '/supplier-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'SupplierRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/supplier-record/supplier-records.html',
                    controller: 'SupplierRecordController',
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
        .state('supplier-record-detail', {
            parent: 'supplier-record',
            url: '/supplier-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'SupplierRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/supplier-record/supplier-record-detail.html',
                    controller: 'SupplierRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'SupplierRecord', function($stateParams, SupplierRecord) {
                    return SupplierRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'supplier-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('supplier-record-detail.edit', {
            parent: 'supplier-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier-record/supplier-record-dialog.html',
                    controller: 'SupplierRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SupplierRecord', function(SupplierRecord) {
                            return SupplierRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('supplier-record.new', {
            parent: 'supplier-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier-record/supplier-record-dialog.html',
                    controller: 'SupplierRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                companyId: null,
                                mnemonicCode: null,
                                supplierStatus: null,
                                supplierName: null,
                                taxNumber: null,
                                phone: null,
                                openBank: null,
                                bankAccount: null,
                                unitAddress: null,
                                contact: null,
                                contactPhone: null,
                                paytype: null,
                                founder: null,
                                founderTime: null,
                                modifier: null,
                                modifierTime: null,
                                remark: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('supplier-record', null, { reload: 'supplier-record' });
                }, function() {
                    $state.go('supplier-record');
                });
            }]
        })
        .state('supplier-record.edit', {
            parent: 'supplier-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier-record/supplier-record-dialog.html',
                    controller: 'SupplierRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SupplierRecord', function(SupplierRecord) {
                            return SupplierRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('supplier-record', null, { reload: 'supplier-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('supplier-record.delete', {
            parent: 'supplier-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/supplier-record/supplier-record-delete-dialog.html',
                    controller: 'SupplierRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['SupplierRecord', function(SupplierRecord) {
                            return SupplierRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('supplier-record', null, { reload: 'supplier-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
