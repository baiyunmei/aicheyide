(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('driver-message', {
            parent: 'entity',
            url: '/driver-message?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DriverMessages'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/driver-message/driver-messages.html',
                    controller: 'DriverMessageController',
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
        .state('driver-message-detail', {
            parent: 'driver-message',
            url: '/driver-message/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DriverMessage'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/driver-message/driver-message-detail.html',
                    controller: 'DriverMessageDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DriverMessage', function($stateParams, DriverMessage) {
                    return DriverMessage.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'driver-message',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('driver-message-detail.edit', {
            parent: 'driver-message-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/driver-message/driver-message-dialog.html',
                    controller: 'DriverMessageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DriverMessage', function(DriverMessage) {
                            return DriverMessage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('driver-message.new', {
            parent: 'driver-message',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/driver-message/driver-message-dialog.html',
                    controller: 'DriverMessageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                companyId: null,
                                certificateType: null,
                                certificatePhone: null,
                                driverName: null,
                                bormDate: null,
                                sex: null,
                                marriageStatus: null,
                                phone: null,
                                educationDegree: null,
                                registered: null,
                                censusRegister: null,
                                housingType: null,
                                contactAddress: null,
                                houseLoan: null,
                                deadline: null,
                                monthly: null,
                                residentialAddress: null,
                                dwellTime: null,
                                identityFront: null,
                                identityReverse: null,
                                drivingFront: null,
                                drivingReverse: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('driver-message', null, { reload: 'driver-message' });
                }, function() {
                    $state.go('driver-message');
                });
            }]
        })
        .state('driver-message.edit', {
            parent: 'driver-message',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/driver-message/driver-message-dialog.html',
                    controller: 'DriverMessageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DriverMessage', function(DriverMessage) {
                            return DriverMessage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('driver-message', null, { reload: 'driver-message' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('driver-message.delete', {
            parent: 'driver-message',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/driver-message/driver-message-delete-dialog.html',
                    controller: 'DriverMessageDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DriverMessage', function(DriverMessage) {
                            return DriverMessage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('driver-message', null, { reload: 'driver-message' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
