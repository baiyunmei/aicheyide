(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('overdue-gathering', {
            parent: 'entity',
            url: '/overdue-gathering?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'OverdueGatherings'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/overdue-gathering/overdue-gatherings.html',
                    controller: 'OverdueGatheringController',
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
        .state('overdue-gathering-detail', {
            parent: 'overdue-gathering',
            url: '/overdue-gathering/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'OverdueGathering'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/overdue-gathering/overdue-gathering-detail.html',
                    controller: 'OverdueGatheringDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'OverdueGathering', function($stateParams, OverdueGathering) {
                    return OverdueGathering.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'overdue-gathering',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('overdue-gathering-detail.edit', {
            parent: 'overdue-gathering-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/overdue-gathering/overdue-gathering-dialog.html',
                    controller: 'OverdueGatheringDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OverdueGathering', function(OverdueGathering) {
                            return OverdueGathering.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('overdue-gathering.new', {
            parent: 'overdue-gathering',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/overdue-gathering/overdue-gathering-dialog.html',
                    controller: 'OverdueGatheringDialogController',
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
                                repaymentTime: null,
                                overdueAwait: null,
                                overdueInterest: null,
                                overdueData: null,
                                total: null,
                                receiptNumber: null,
                                receiptDate: null,
                                practicalMoney: null,
                                paymentWay: null,
                                remark1: null,
                                founder: null,
                                founderTime: null,
                                modifier: null,
                                modifierTime: null,
                                gathering: null,
                                gatherTime: null,
                                remark2: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('overdue-gathering', null, { reload: 'overdue-gathering' });
                }, function() {
                    $state.go('overdue-gathering');
                });
            }]
        })
        .state('overdue-gathering.edit', {
            parent: 'overdue-gathering',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/overdue-gathering/overdue-gathering-dialog.html',
                    controller: 'OverdueGatheringDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OverdueGathering', function(OverdueGathering) {
                            return OverdueGathering.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('overdue-gathering', null, { reload: 'overdue-gathering' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('overdue-gathering.delete', {
            parent: 'overdue-gathering',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/overdue-gathering/overdue-gathering-delete-dialog.html',
                    controller: 'OverdueGatheringDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['OverdueGathering', function(OverdueGathering) {
                            return OverdueGathering.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('overdue-gathering', null, { reload: 'overdue-gathering' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
