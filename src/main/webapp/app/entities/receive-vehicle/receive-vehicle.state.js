(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('receive-vehicle', {
            parent: 'entity',
            url: '/receive-vehicle?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ReceiveVehicles'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/receive-vehicle/receive-vehicles.html',
                    controller: 'ReceiveVehicleController',
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
        .state('receive-vehicle-detail', {
            parent: 'receive-vehicle',
            url: '/receive-vehicle/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ReceiveVehicle'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/receive-vehicle/receive-vehicle-detail.html',
                    controller: 'ReceiveVehicleDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'ReceiveVehicle', function($stateParams, ReceiveVehicle) {
                    return ReceiveVehicle.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'receive-vehicle',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('receive-vehicle-detail.edit', {
            parent: 'receive-vehicle-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/receive-vehicle/receive-vehicle-dialog.html',
                    controller: 'ReceiveVehicleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ReceiveVehicle', function(ReceiveVehicle) {
                            return ReceiveVehicle.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('receive-vehicle.new', {
            parent: 'receive-vehicle',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/receive-vehicle/receive-vehicle-dialog.html',
                    controller: 'ReceiveVehicleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                vehicleId: null,
                                informVehicleTime: null,
                                informData: null,
                                informVehicleDate: null,
                                remark: null,
                                parkWarehouse: null,
                                founder: null,
                                founderTime: null,
                                modifier: null,
                                modifierTime: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('receive-vehicle', null, { reload: 'receive-vehicle' });
                }, function() {
                    $state.go('receive-vehicle');
                });
            }]
        })
        .state('receive-vehicle.edit', {
            parent: 'receive-vehicle',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/receive-vehicle/receive-vehicle-dialog.html',
                    controller: 'ReceiveVehicleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ReceiveVehicle', function(ReceiveVehicle) {
                            return ReceiveVehicle.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('receive-vehicle', null, { reload: 'receive-vehicle' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('receive-vehicle.delete', {
            parent: 'receive-vehicle',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/receive-vehicle/receive-vehicle-delete-dialog.html',
                    controller: 'ReceiveVehicleDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ReceiveVehicle', function(ReceiveVehicle) {
                            return ReceiveVehicle.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('receive-vehicle', null, { reload: 'receive-vehicle' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
