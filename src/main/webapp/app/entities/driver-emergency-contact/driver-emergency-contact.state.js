(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('driver-emergency-contact', {
            parent: 'entity',
            url: '/driver-emergency-contact?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DriverEmergencyContacts'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/driver-emergency-contact/driver-emergency-contacts.html',
                    controller: 'DriverEmergencyContactController',
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
        .state('driver-emergency-contact-detail', {
            parent: 'driver-emergency-contact',
            url: '/driver-emergency-contact/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DriverEmergencyContact'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/driver-emergency-contact/driver-emergency-contact-detail.html',
                    controller: 'DriverEmergencyContactDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DriverEmergencyContact', function($stateParams, DriverEmergencyContact) {
                    return DriverEmergencyContact.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'driver-emergency-contact',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('driver-emergency-contact-detail.edit', {
            parent: 'driver-emergency-contact-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/driver-emergency-contact/driver-emergency-contact-dialog.html',
                    controller: 'DriverEmergencyContactDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DriverEmergencyContact', function(DriverEmergencyContact) {
                            return DriverEmergencyContact.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('driver-emergency-contact.new', {
            parent: 'driver-emergency-contact',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/driver-emergency-contact/driver-emergency-contact-dialog.html',
                    controller: 'DriverEmergencyContactDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name1: null,
                                residentialAddress1: null,
                                phone1: null,
                                relation1: null,
                                name2: null,
                                residentialAddress2: null,
                                phone2: null,
                                relation2: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('driver-emergency-contact', null, { reload: 'driver-emergency-contact' });
                }, function() {
                    $state.go('driver-emergency-contact');
                });
            }]
        })
        .state('driver-emergency-contact.edit', {
            parent: 'driver-emergency-contact',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/driver-emergency-contact/driver-emergency-contact-dialog.html',
                    controller: 'DriverEmergencyContactDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DriverEmergencyContact', function(DriverEmergencyContact) {
                            return DriverEmergencyContact.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('driver-emergency-contact', null, { reload: 'driver-emergency-contact' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('driver-emergency-contact.delete', {
            parent: 'driver-emergency-contact',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/driver-emergency-contact/driver-emergency-contact-delete-dialog.html',
                    controller: 'DriverEmergencyContactDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DriverEmergencyContact', function(DriverEmergencyContact) {
                            return DriverEmergencyContact.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('driver-emergency-contact', null, { reload: 'driver-emergency-contact' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
