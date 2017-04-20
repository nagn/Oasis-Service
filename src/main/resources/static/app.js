var oasisApp = angular.module('oasisApp', ['ui.router']);
oasisApp.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when('','/home');

    $urlRouterProvider.otherwise('/home');

    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: 'views/home-view.html'
        });
});