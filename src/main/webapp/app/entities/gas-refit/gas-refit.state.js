(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('gas-refit', {
            parent: 'entity',
            url: '/gas-refit?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'GasRefits'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/gas-refit/gas-refits.html',
                    controller: 'GasRefitController',
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
        .state('gas-refit-detail', {
            parent: 'gas-refit',
            url: '/gas-refit/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'GasRefit'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/gas-refit/gas-refit-detail.html',
                    controller: 'GasRefitDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'GasRefit', function($stateParams, GasRefit) {
                    return GasRefit.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'gas-refit',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('gas-refit-detail.edit', {
            parent: 'gas-refit-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/gas-refit/gas-refit-dialog.html',
                    controller: 'GasRefitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['GasRefit', function(GasRefit) {
                            return GasRefit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('gas-refit.new', {
            parent: 'gas-refit',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/gas-refit/gas-refit-dialog.html',
                    controller: 'GasRefitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                vehicleId: null,
                                refit: null,
                                remark: null,
                                refitRecord: null,
                                refitTime: null,
                                refitUnit: null,
                                phone: null,
                                founder: null,
                                founderTime: null,
                                modifier: null,
                                modifierTime: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('gas-refit', null, { reload: 'gas-refit' });
                }, function() {
                    $state.go('gas-refit');
                });
            }]
        })
        .state('gas-refit.edit', {
            parent: 'gas-refit',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/gas-refit/gas-refit-dialog.html',
                    controller: 'GasRefitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['GasRefit', function(GasRefit) {
                            return GasRefit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('gas-refit', null, { reload: 'gas-refit' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('gas-refit.delete', {
            parent: 'gas-refit',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/gas-refit/gas-refit-delete-dialog.html',
                    controller: 'GasRefitDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['GasRefit', function(GasRefit) {
                            return GasRefit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('gas-refit', null, { reload: 'gas-refit' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
