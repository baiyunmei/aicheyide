(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('vehicle', {
            parent: 'entity',
            url: '/vehicle?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Vehicles'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/vehicle/vehicles.html',
                    controller: 'VehicleController',
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
        .state('vehicle-detail', {
            parent: 'vehicle',
            url: '/vehicle/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Vehicle'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/vehicle/vehicle-detail.html',
                    controller: 'VehicleDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Vehicle', function($stateParams, Vehicle) {
                    return Vehicle.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'vehicle',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('vehicle-detail.edit', {
            parent: 'vehicle-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/vehicle/vehicle-dialog.html',
                    controller: 'VehicleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Vehicle', function(Vehicle) {
                            return Vehicle.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('vehicle.new', {
            parent: 'vehicle',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/vehicle/vehicle-dialog.html',
                    controller: 'VehicleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                companyId: null,
                                engine: null,
                                vehicleShelf: null,
                                plateNumber: null,
                                driverId: null,
                                driverName: null,
                                vehicleColour: null,
                                status: null,
                                stopLocation: null,
                                brandType: null,
                                company: null,
                                vehicleType: null,
                                remark: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('vehicle', null, { reload: 'vehicle' });
                }, function() {
                    $state.go('vehicle');
                });
            }]
        })
        .state('vehicle.edit', {
            parent: 'vehicle',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/vehicle/vehicle-dialog.html',
                    controller: 'VehicleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Vehicle', function(Vehicle) {
                            return Vehicle.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('vehicle', null, { reload: 'vehicle' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('vehicle.delete', {
            parent: 'vehicle',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/vehicle/vehicle-delete-dialog.html',
                    controller: 'VehicleDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Vehicle', function(Vehicle) {
                            return Vehicle.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('vehicle', null, { reload: 'vehicle' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
