(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('purchase-tax', {
            parent: 'entity',
            url: '/purchase-tax?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PurchaseTaxes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/purchase-tax/purchase-taxes.html',
                    controller: 'PurchaseTaxController',
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
        .state('purchase-tax-detail', {
            parent: 'purchase-tax',
            url: '/purchase-tax/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PurchaseTax'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/purchase-tax/purchase-tax-detail.html',
                    controller: 'PurchaseTaxDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'PurchaseTax', function($stateParams, PurchaseTax) {
                    return PurchaseTax.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'purchase-tax',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('purchase-tax-detail.edit', {
            parent: 'purchase-tax-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/purchase-tax/purchase-tax-dialog.html',
                    controller: 'PurchaseTaxDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PurchaseTax', function(PurchaseTax) {
                            return PurchaseTax.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('purchase-tax.new', {
            parent: 'purchase-tax',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/purchase-tax/purchase-tax-dialog.html',
                    controller: 'PurchaseTaxDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                vehicleId: null,
                                purchaseTaxmoney: null,
                                remark: null,
                                invoicePicture: null,
                                purchaseTime: null,
                                founder: null,
                                founderTime: null,
                                modifier: null,
                                modifierTime: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('purchase-tax', null, { reload: 'purchase-tax' });
                }, function() {
                    $state.go('purchase-tax');
                });
            }]
        })
        .state('purchase-tax.edit', {
            parent: 'purchase-tax',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/purchase-tax/purchase-tax-dialog.html',
                    controller: 'PurchaseTaxDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PurchaseTax', function(PurchaseTax) {
                            return PurchaseTax.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('purchase-tax', null, { reload: 'purchase-tax' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('purchase-tax.delete', {
            parent: 'purchase-tax',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/purchase-tax/purchase-tax-delete-dialog.html',
                    controller: 'PurchaseTaxDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PurchaseTax', function(PurchaseTax) {
                            return PurchaseTax.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('purchase-tax', null, { reload: 'purchase-tax' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
