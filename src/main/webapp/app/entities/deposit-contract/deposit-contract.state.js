(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('deposit-contract', {
            parent: 'entity',
            url: '/deposit-contract?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DepositContracts'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/deposit-contract/deposit-contracts.html',
                    controller: 'DepositContractController',
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
        .state('deposit-contract-detail', {
            parent: 'deposit-contract',
            url: '/deposit-contract/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DepositContract'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/deposit-contract/deposit-contract-detail.html',
                    controller: 'DepositContractDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DepositContract', function($stateParams, DepositContract) {
                    return DepositContract.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'deposit-contract',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('deposit-contract-detail.edit', {
            parent: 'deposit-contract-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/deposit-contract/deposit-contract-dialog.html',
                    controller: 'DepositContractDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DepositContract', function(DepositContract) {
                            return DepositContract.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('deposit-contract.new', {
            parent: 'deposit-contract',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/deposit-contract/deposit-contract-dialog.html',
                    controller: 'DepositContractDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                orderId: null,
                                driverId: null,
                                auditResult: null,
                                audit: null,
                                auditId: null,
                                auditTime: null,
                                remark: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('deposit-contract', null, { reload: 'deposit-contract' });
                }, function() {
                    $state.go('deposit-contract');
                });
            }]
        })
        .state('deposit-contract.edit', {
            parent: 'deposit-contract',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/deposit-contract/deposit-contract-dialog.html',
                    controller: 'DepositContractDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DepositContract', function(DepositContract) {
                            return DepositContract.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('deposit-contract', null, { reload: 'deposit-contract' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('deposit-contract.delete', {
            parent: 'deposit-contract',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/deposit-contract/deposit-contract-delete-dialog.html',
                    controller: 'DepositContractDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DepositContract', function(DepositContract) {
                            return DepositContract.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('deposit-contract', null, { reload: 'deposit-contract' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
