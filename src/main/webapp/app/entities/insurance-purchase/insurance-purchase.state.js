(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('insurance-purchase', {
            parent: 'entity',
            url: '/insurance-purchase?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'InsurancePurchases'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/insurance-purchase/insurance-purchases.html',
                    controller: 'InsurancePurchaseController',
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
        .state('insurance-purchase-detail', {
            parent: 'insurance-purchase',
            url: '/insurance-purchase/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'InsurancePurchase'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/insurance-purchase/insurance-purchase-detail.html',
                    controller: 'InsurancePurchaseDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'InsurancePurchase', function($stateParams, InsurancePurchase) {
                    return InsurancePurchase.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'insurance-purchase',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('insurance-purchase-detail.edit', {
            parent: 'insurance-purchase-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/insurance-purchase/insurance-purchase-dialog.html',
                    controller: 'InsurancePurchaseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['InsurancePurchase', function(InsurancePurchase) {
                            return InsurancePurchase.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('insurance-purchase.new', {
            parent: 'insurance-purchase',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/insurance-purchase/insurance-purchase-dialog.html',
                    controller: 'InsurancePurchaseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                vehicleId: null,
                                insuranceCompany: null,
                                insuranceMoney: null,
                                policyNumber: null,
                                ibeginDate: null,
                                ifinishDate: null,
                                icompanyName: null,
                                commercialCompany: null,
                                commercialMoney: null,
                                commercialNumber: null,
                                cbeginDate: null,
                                cfinishDate: null,
                                ccompanyName: null,
                                remark: null,
                                iphotograph: null,
                                cphotograph: null,
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
                    $state.go('insurance-purchase', null, { reload: 'insurance-purchase' });
                }, function() {
                    $state.go('insurance-purchase');
                });
            }]
        })
        .state('insurance-purchase.edit', {
            parent: 'insurance-purchase',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/insurance-purchase/insurance-purchase-dialog.html',
                    controller: 'InsurancePurchaseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['InsurancePurchase', function(InsurancePurchase) {
                            return InsurancePurchase.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('insurance-purchase', null, { reload: 'insurance-purchase' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('insurance-purchase.delete', {
            parent: 'insurance-purchase',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/insurance-purchase/insurance-purchase-delete-dialog.html',
                    controller: 'InsurancePurchaseDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['InsurancePurchase', function(InsurancePurchase) {
                            return InsurancePurchase.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('insurance-purchase', null, { reload: 'insurance-purchase' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
