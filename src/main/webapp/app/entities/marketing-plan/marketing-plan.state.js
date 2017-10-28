(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('marketing-plan', {
            parent: 'entity',
            url: '/marketing-plan?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'MarketingPlans'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/marketing-plan/marketing-plans.html',
                    controller: 'MarketingPlanController',
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
        .state('marketing-plan-detail', {
            parent: 'marketing-plan',
            url: '/marketing-plan/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'MarketingPlan'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/marketing-plan/marketing-plan-detail.html',
                    controller: 'MarketingPlanDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'MarketingPlan', function($stateParams, MarketingPlan) {
                    return MarketingPlan.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'marketing-plan',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('marketing-plan-detail.edit', {
            parent: 'marketing-plan-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/marketing-plan/marketing-plan-dialog.html',
                    controller: 'MarketingPlanDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MarketingPlan', function(MarketingPlan) {
                            return MarketingPlan.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('marketing-plan.new', {
            parent: 'marketing-plan',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/marketing-plan/marketing-plan-dialog.html',
                    controller: 'MarketingPlanDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('marketing-plan', null, { reload: 'marketing-plan' });
                }, function() {
                    $state.go('marketing-plan');
                });
            }]
        })
        .state('marketing-plan.edit', {
            parent: 'marketing-plan',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/marketing-plan/marketing-plan-dialog.html',
                    controller: 'MarketingPlanDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MarketingPlan', function(MarketingPlan) {
                            return MarketingPlan.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('marketing-plan', null, { reload: 'marketing-plan' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('marketing-plan.delete', {
            parent: 'marketing-plan',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/marketing-plan/marketing-plan-delete-dialog.html',
                    controller: 'MarketingPlanDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['MarketingPlan', function(MarketingPlan) {
                            return MarketingPlan.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('marketing-plan', null, { reload: 'marketing-plan' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
