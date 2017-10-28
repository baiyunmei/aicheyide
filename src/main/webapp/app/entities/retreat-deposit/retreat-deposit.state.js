(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('retreat-deposit', {
            parent: 'entity',
            url: '/retreat-deposit?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RetreatDeposits'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/retreat-deposit/retreat-deposits.html',
                    controller: 'RetreatDepositController',
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
        .state('retreat-deposit-detail', {
            parent: 'retreat-deposit',
            url: '/retreat-deposit/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'RetreatDeposit'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/retreat-deposit/retreat-deposit-detail.html',
                    controller: 'RetreatDepositDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'RetreatDeposit', function($stateParams, RetreatDeposit) {
                    return RetreatDeposit.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'retreat-deposit',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('retreat-deposit-detail.edit', {
            parent: 'retreat-deposit-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/retreat-deposit/retreat-deposit-dialog.html',
                    controller: 'RetreatDepositDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RetreatDeposit', function(RetreatDeposit) {
                            return RetreatDeposit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('retreat-deposit.new', {
            parent: 'retreat-deposit',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/retreat-deposit/retreat-deposit-dialog.html',
                    controller: 'RetreatDepositDialogController',
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
                                plateNumber: null,
                                driverName: null,
                                refundTime: null,
                                money: null,
                                source: null,
                                pledgeStatus: null,
                                remark: null,
                                receiptNumber: null,
                                receiptDate: null,
                                nextRentTime: null,
                                founder: null,
                                founderTime: null,
                                modifier: null,
                                modifierTime: null,
                                whetherRefund: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('retreat-deposit', null, { reload: 'retreat-deposit' });
                }, function() {
                    $state.go('retreat-deposit');
                });
            }]
        })
        .state('retreat-deposit.edit', {
            parent: 'retreat-deposit',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/retreat-deposit/retreat-deposit-dialog.html',
                    controller: 'RetreatDepositDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['RetreatDeposit', function(RetreatDeposit) {
                            return RetreatDeposit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('retreat-deposit', null, { reload: 'retreat-deposit' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('retreat-deposit.delete', {
            parent: 'retreat-deposit',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/retreat-deposit/retreat-deposit-delete-dialog.html',
                    controller: 'RetreatDepositDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['RetreatDeposit', function(RetreatDeposit) {
                            return RetreatDeposit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('retreat-deposit', null, { reload: 'retreat-deposit' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
