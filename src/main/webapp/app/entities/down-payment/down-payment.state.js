(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('down-payment', {
            parent: 'entity',
            url: '/down-payment?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DownPayments'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/down-payment/down-payments.html',
                    controller: 'DownPaymentController',
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
        .state('down-payment-detail', {
            parent: 'down-payment',
            url: '/down-payment/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DownPayment'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/down-payment/down-payment-detail.html',
                    controller: 'DownPaymentDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DownPayment', function($stateParams, DownPayment) {
                    return DownPayment.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'down-payment',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('down-payment-detail.edit', {
            parent: 'down-payment-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/down-payment/down-payment-dialog.html',
                    controller: 'DownPaymentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DownPayment', function(DownPayment) {
                            return DownPayment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('down-payment.new', {
            parent: 'down-payment',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/down-payment/down-payment-dialog.html',
                    controller: 'DownPaymentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                companyId: null,
                                orderId: null,
                                receiptMoney: null,
                                receiptWater: null,
                                accountNumber: null,
                                receipt: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('down-payment', null, { reload: 'down-payment' });
                }, function() {
                    $state.go('down-payment');
                });
            }]
        })
        .state('down-payment.edit', {
            parent: 'down-payment',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/down-payment/down-payment-dialog.html',
                    controller: 'DownPaymentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DownPayment', function(DownPayment) {
                            return DownPayment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('down-payment', null, { reload: 'down-payment' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('down-payment.delete', {
            parent: 'down-payment',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/down-payment/down-payment-delete-dialog.html',
                    controller: 'DownPaymentDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DownPayment', function(DownPayment) {
                            return DownPayment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('down-payment', null, { reload: 'down-payment' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
