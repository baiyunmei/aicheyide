(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('company-message', {
            parent: 'entity',
            url: '/company-message?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'CompanyMessages'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/company-message/company-messages.html',
                    controller: 'CompanyMessageController',
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
        .state('company-message-detail', {
            parent: 'company-message',
            url: '/company-message/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'CompanyMessage'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/company-message/company-message-detail.html',
                    controller: 'CompanyMessageDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'CompanyMessage', function($stateParams, CompanyMessage) {
                    return CompanyMessage.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'company-message',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('company-message-detail.edit', {
            parent: 'company-message-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/company-message/company-message-dialog.html',
                    controller: 'CompanyMessageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CompanyMessage', function(CompanyMessage) {
                            return CompanyMessage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('company-message.new', {
            parent: 'company-message',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/company-message/company-message-dialog.html',
                    controller: 'CompanyMessageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                companyName: null,
                                companyLogo: null,
                                companyPrincipalId: null,
                                companyPrincipal: null,
                                companyPrincipalphone: null,
                                site: null,
                                accountName: null,
                                openingBank: null,
                                phone: null,
                                taxNumber: null,
                                referrals: null,
                                referralsPhone: null,
                                referralsAuthorizationId: null,
                                authorizationId: null,
                                authorizationCompanyName: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('company-message', null, { reload: 'company-message' });
                }, function() {
                    $state.go('company-message');
                });
            }]
        })
        .state('company-message.edit', {
            parent: 'company-message',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/company-message/company-message-dialog.html',
                    controller: 'CompanyMessageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CompanyMessage', function(CompanyMessage) {
                            return CompanyMessage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('company-message', null, { reload: 'company-message' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('company-message.delete', {
            parent: 'company-message',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/company-message/company-message-delete-dialog.html',
                    controller: 'CompanyMessageDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CompanyMessage', function(CompanyMessage) {
                            return CompanyMessage.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('company-message', null, { reload: 'company-message' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
