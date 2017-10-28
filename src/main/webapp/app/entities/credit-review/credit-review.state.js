(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('credit-review', {
            parent: 'entity',
            url: '/credit-review?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'CreditReviews'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/credit-review/credit-reviews.html',
                    controller: 'CreditReviewController',
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
        .state('credit-review-detail', {
            parent: 'credit-review',
            url: '/credit-review/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'CreditReview'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/credit-review/credit-review-detail.html',
                    controller: 'CreditReviewDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'CreditReview', function($stateParams, CreditReview) {
                    return CreditReview.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'credit-review',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('credit-review-detail.edit', {
            parent: 'credit-review-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/credit-review/credit-review-dialog.html',
                    controller: 'CreditReviewDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CreditReview', function(CreditReview) {
                            return CreditReview.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('credit-review.new', {
            parent: 'credit-review',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/credit-review/credit-review-dialog.html',
                    controller: 'CreditReviewDialogController',
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
                    $state.go('credit-review', null, { reload: 'credit-review' });
                }, function() {
                    $state.go('credit-review');
                });
            }]
        })
        .state('credit-review.edit', {
            parent: 'credit-review',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/credit-review/credit-review-dialog.html',
                    controller: 'CreditReviewDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CreditReview', function(CreditReview) {
                            return CreditReview.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('credit-review', null, { reload: 'credit-review' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('credit-review.delete', {
            parent: 'credit-review',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/credit-review/credit-review-delete-dialog.html',
                    controller: 'CreditReviewDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CreditReview', function(CreditReview) {
                            return CreditReview.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('credit-review', null, { reload: 'credit-review' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
