var oasisApp = angular.module('oasisApp', ['ui.router', 'ui.bootstrap']);
oasisApp.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when('', '/welcome');
    $urlRouterProvider.otherwise('/login')

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
        });

});