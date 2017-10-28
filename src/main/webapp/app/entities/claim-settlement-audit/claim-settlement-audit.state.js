(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('claim-settlement-audit', {
            parent: 'entity',
            url: '/claim-settlement-audit?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ClaimSettlementAudits'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/claim-settlement-audit/claim-settlement-audits.html',
                    controller: 'ClaimSettlementAuditController',
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
        .state('claim-settlement-audit-detail', {
            parent: 'claim-settlement-audit',
            url: '/claim-settlement-audit/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ClaimSettlementAudit'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/claim-settlement-audit/claim-settlement-audit-detail.html',
                    controller: 'ClaimSettlementAuditDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'ClaimSettlementAudit', function($stateParams, ClaimSettlementAudit) {
                    return ClaimSettlementAudit.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'claim-settlement-audit',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('claim-settlement-audit-detail.edit', {
            parent: 'claim-settlement-audit-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/claim-settlement-audit/claim-settlement-audit-dialog.html',
                    controller: 'ClaimSettlementAuditDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ClaimSettlementAudit', function(ClaimSettlementAudit) {
                            return ClaimSettlementAudit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('claim-settlement-audit.new', {
            parent: 'claim-settlement-audit',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/claim-settlement-audit/claim-settlement-audit-dialog.html',
                    controller: 'ClaimSettlementAuditDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                driverId: null,
                                orderId: null,
                                companyId: null,
                                vehicleId: null,
                                claimMoney: null,
                                illegalNaam: null,
                                illegalNumber: null,
                                illegalAccessory: null,
                                payment: null,
                                repairAccessory: null,
                                debt: null,
                                debtAccessory: null,
                                debtRemark: null,
                                actualPayment: null,
                                paymentTime: null,
                                accountNumber: null,
                                paymentWay: null,
                                serialNumber: null,
                                auditStatus: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('claim-settlement-audit', null, { reload: 'claim-settlement-audit' });
                }, function() {
                    $state.go('claim-settlement-audit');
                });
            }]
        })
        .state('claim-settlement-audit.edit', {
            parent: 'claim-settlement-audit',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/claim-settlement-audit/claim-settlement-audit-dialog.html',
                    controller: 'ClaimSettlementAuditDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ClaimSettlementAudit', function(ClaimSettlementAudit) {
                            return ClaimSettlementAudit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('claim-settlement-audit', null, { reload: 'claim-settlement-audit' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('claim-settlement-audit.delete', {
            parent: 'claim-settlement-audit',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/claim-settlement-audit/claim-settlement-audit-delete-dialog.html',
                    controller: 'ClaimSettlementAuditDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ClaimSettlementAudit', function(ClaimSettlementAudit) {
                            return ClaimSettlementAudit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('claim-settlement-audit', null, { reload: 'claim-settlement-audit' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
