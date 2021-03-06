var oasisApp = angular.module('oasisApp', ['ui.router', 'ui.bootstrap']);
oasisApp.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when('', '/welcome');
    $urlRouterProvider.otherwise('/welcome');

    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: 'views/home-view.html',
            controller: 'homeController'
        })
        .state('login', {
            url: '/login',
            templateUrl: 'views/login-view.html',
            controller: 'loginController'
        })
       .state('welcome', {
            url: '/welcome',
            templateUrl: 'views/welcome-view.html',
            controller: 'welcomeController'
        })
       .state('register', {
            url: '/register',
            templateUrl: 'views/register-view.html',
            controller: 'registerController'
        })
       .state('edit-profile', {
            url: '/edit-profile',
            templateUrl: 'views/edit-profile-view.html',
            controller: 'editProfileController'
        })
       .state('map', {
            url: '/map',
            templateUrl: 'views/map-view.html',
            controller: 'mapController'
        })
        .state('create-source-report', {
            url: '/create-source-report',
            templateUrl: 'views/create-source-report-view.html',
            controller: 'createSourceReportController'
        })
        .state('create-quality-report', {
            url: '/create-purity-report',
            templateUrl: 'views/create-quality-report-view.html',
            controller: 'createQualityReportController'
        })
        .state('security-log', {
            url: '/security-log',
            templateUrl: 'views/security-logs-view.html',
            controller: 'logController'
        })
        .state('unblock-user', {
            url: '/unblock-user',
            templateUrl: 'views/unblock-user-view.html',
            controller: 'unblockController'
        });


});