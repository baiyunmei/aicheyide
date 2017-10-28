(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('please-payee-audit', {
            parent: 'entity',
            url: '/please-payee-audit?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PleasePayeeAudits'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/please-payee-audit/please-payee-audits.html',
                    controller: 'PleasePayeeAuditController',
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
        .state('please-payee-audit-detail', {
            parent: 'please-payee-audit',
            url: '/please-payee-audit/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PleasePayeeAudit'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/please-payee-audit/please-payee-audit-detail.html',
                    controller: 'PleasePayeeAuditDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'PleasePayeeAudit', function($stateParams, PleasePayeeAudit) {
                    return PleasePayeeAudit.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'please-payee-audit',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('please-payee-audit-detail.edit', {
            parent: 'please-payee-audit-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/please-payee-audit/please-payee-audit-dialog.html',
                    controller: 'PleasePayeeAuditDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PleasePayeeAudit', function(PleasePayeeAudit) {
                            return PleasePayeeAudit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('please-payee-audit.new', {
            parent: 'please-payee-audit',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/please-payee-audit/please-payee-audit-dialog.html',
                    controller: 'PleasePayeeAuditDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                companyId: null,
                                orderId: null,
                                receiptNumber: null,
                                receiptDate: null,
                                pleasePayeeId: null,
                                pleasePayeeName: null,
                                pleasePayeeMoney: null,
                                reason: null,
                                gatheringName: null,
                                gatheringNumber: null,
                                useTime: null,
                                remark1: null,
                                accessory: null,
                                status: null,
                                remark2: null,
                                remit: null,
                                serialNumber: null,
                                establishTime: null,
                                amendTime: null,
                                amendPerson: null,
                                submitter: null,
                                submitTime: null,
                                auditor: null,
                                auditorTime: null,
                                conditions: null,
                                pleasePayeeType: null,
                                vehicleShelf: null,
                                engine: null,
                                driverId: null,
                                driverName: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('please-payee-audit', null, { reload: 'please-payee-audit' });
                }, function() {
                    $state.go('please-payee-audit');
                });
            }]
        })
        .state('please-payee-audit.edit', {
            parent: 'please-payee-audit',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/please-payee-audit/please-payee-audit-dialog.html',
                    controller: 'PleasePayeeAuditDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PleasePayeeAudit', function(PleasePayeeAudit) {
                            return PleasePayeeAudit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('please-payee-audit', null, { reload: 'please-payee-audit' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('please-payee-audit.delete', {
            parent: 'please-payee-audit',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/please-payee-audit/please-payee-audit-delete-dialog.html',
                    controller: 'PleasePayeeAuditDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PleasePayeeAudit', function(PleasePayeeAudit) {
                            return PleasePayeeAudit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('please-payee-audit', null, { reload: 'please-payee-audit' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
