(function() {
    'use strict';

    angular
        .module('aicheyideApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('validate-record', {
            parent: 'entity',
            url: '/validate-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ValidateRecords'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/validate-record/validate-records.html',
                    controller: 'ValidateRecordController',
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
        .state('validate-record-detail', {
            parent: 'validate-record',
            url: '/validate-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ValidateRecord'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/validate-record/validate-record-detail.html',
                    controller: 'ValidateRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'ValidateRecord', function($stateParams, ValidateRecord) {
                    return ValidateRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'validate-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('validate-record-detail.edit', {
            parent: 'validate-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/validate-record/validate-record-dialog.html',
                    controller: 'ValidateRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ValidateRecord', function(ValidateRecord) {
                            return ValidateRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('validate-record.new', {
            parent: 'validate-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/validate-record/validate-record-dialog.html',
                    controller: 'ValidateRecordDialogController',
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
                    $state.go('validate-record', null, { reload: 'validate-record' });
                }, function() {
                    $state.go('validate-record');
                });
            }]
        })
        .state('validate-record.edit', {
            parent: 'validate-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/validate-record/validate-record-dialog.html',
                    controller: 'ValidateRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ValidateRecord', function(ValidateRecord) {
                            return ValidateRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('validate-record', null, { reload: 'validate-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('validate-record.delete', {
            parent: 'validate-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/validate-record/validate-record-delete-dialog.html',
                    controller: 'ValidateRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ValidateRecord', function(ValidateRecord) {
                            return ValidateRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('validate-record', null, { reload: 'validate-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
