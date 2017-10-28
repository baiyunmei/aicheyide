(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('gps-refit', {
            parent: 'entity',
            url: '/gps-refit?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'GPSRefits'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/g-ps-refit/g-ps-refits.html',
                    controller: 'GPSRefitController',
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
        .state('gps-refit-detail', {
            parent: 'gps-refit',
            url: '/gps-refit/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'GPSRefit'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/g-ps-refit/gps-refit-detail.html',
                    controller: 'GPSRefitDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'GPSRefit', function($stateParams, GPSRefit) {
                    return GPSRefit.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'gps-refit',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('gps-refit-detail.edit', {
            parent: 'gps-refit-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/g-ps-refit/gps-refit-dialog.html',
                    controller: 'GPSRefitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['GPSRefit', function(GPSRefit) {
                            return GPSRefit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('gps-refit.new', {
            parent: 'gps-refit',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/g-ps-refit/gps-refit-dialog.html',
                    controller: 'GPSRefitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                driverId: null,
                                companyId: null,
                                vehicleId: null,
                                receiptNumber: null,
                                receiptDate: null,
                                operatingDate: null,
                                plateNumber: null,
                                validateTime: null,
                                kilometre: null,
                                damage: null,
                                describes: null,
                                damagepIcture: null,
                                remark: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('gps-refit', null, { reload: 'gps-refit' });
                }, function() {
                    $state.go('gps-refit');
                });
            }]
        })
        .state('gps-refit.edit', {
            parent: 'gps-refit',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/g-ps-refit/gps-refit-dialog.html',
                    controller: 'GPSRefitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['GPSRefit', function(GPSRefit) {
                            return GPSRefit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('gps-refit', null, { reload: 'gps-refit' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('gps-refit.delete', {
            parent: 'gps-refit',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/g-ps-refit/gps-refit-delete-dialog.html',
                    controller: 'GPSRefitDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['GPSRefit', function(GPSRefit) {
                            return GPSRefit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('gps-refit', null, { reload: 'gps-refit' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
