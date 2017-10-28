(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('formal-contract', {
            parent: 'entity',
            url: '/formal-contract?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'FormalContracts'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/formal-contract/formal-contracts.html',
                    controller: 'FormalContractController',
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
        .state('formal-contract-detail', {
            parent: 'formal-contract',
            url: '/formal-contract/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'FormalContract'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/formal-contract/formal-contract-detail.html',
                    controller: 'FormalContractDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'FormalContract', function($stateParams, FormalContract) {
                    return FormalContract.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'formal-contract',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('formal-contract-detail.edit', {
            parent: 'formal-contract-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/formal-contract/formal-contract-dialog.html',
                    controller: 'FormalContractDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FormalContract', function(FormalContract) {
                            return FormalContract.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('formal-contract.new', {
            parent: 'formal-contract',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/formal-contract/formal-contract-dialog.html',
                    controller: 'FormalContractDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                orderId: null,
                                contractNumbering: null,
                                video: null,
                                credit: null,
                                apply: null,
                                special: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('formal-contract', null, { reload: 'formal-contract' });
                }, function() {
                    $state.go('formal-contract');
                });
            }]
        })
        .state('formal-contract.edit', {
            parent: 'formal-contract',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/formal-contract/formal-contract-dialog.html',
                    controller: 'FormalContractDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FormalContract', function(FormalContract) {
                            return FormalContract.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('formal-contract', null, { reload: 'formal-contract' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('formal-contract.delete', {
            parent: 'formal-contract',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/formal-contract/formal-contract-delete-dialog.html',
                    controller: 'FormalContractDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['FormalContract', function(FormalContract) {
                            return FormalContract.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('formal-contract', null, { reload: 'formal-contract' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
