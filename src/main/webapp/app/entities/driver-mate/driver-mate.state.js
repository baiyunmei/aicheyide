(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('driver-mate', {
            parent: 'entity',
            url: '/driver-mate?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DriverMates'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/driver-mate/driver-mates.html',
                    controller: 'DriverMateController',
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
        .state('driver-mate-detail', {
            parent: 'driver-mate',
            url: '/driver-mate/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DriverMate'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/driver-mate/driver-mate-detail.html',
                    controller: 'DriverMateDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DriverMate', function($stateParams, DriverMate) {
                    return DriverMate.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'driver-mate',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('driver-mate-detail.edit', {
            parent: 'driver-mate-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/driver-mate/driver-mate-dialog.html',
                    controller: 'DriverMateDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DriverMate', function(DriverMate) {
                            return DriverMate.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('driver-mate.new', {
            parent: 'driver-mate',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/driver-mate/driver-mate-dialog.html',
                    controller: 'DriverMateDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                driverId: null,
                                name: null,
                                sex: null,
                                phone: null,
                                certificateType: null,
                                certificatePhone: null,
                                location: null,
                                unitName: null,
                                residentialAddress: null,
                                unitAddress: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('driver-mate', null, { reload: 'driver-mate' });
                }, function() {
                    $state.go('driver-mate');
                });
            }]
        })
        .state('driver-mate.edit', {
            parent: 'driver-mate',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/driver-mate/driver-mate-dialog.html',
                    controller: 'DriverMateDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DriverMate', function(DriverMate) {
                            return DriverMate.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('driver-mate', null, { reload: 'driver-mate' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('driver-mate.delete', {
            parent: 'driver-mate',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/driver-mate/driver-mate-delete-dialog.html',
                    controller: 'DriverMateDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DriverMate', function(DriverMate) {
                            return DriverMate.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('driver-mate', null, { reload: 'driver-mate' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
