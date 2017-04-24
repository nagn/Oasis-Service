oasisApp.controller('welcomeController', ['$scope', '$state', 'loginService', function($scope, $state, loginService) {
    if (loginService.isLoggedIn()) {
        $state.transitionTo('home');
    }
    $scope.login = function() {
        $state.transitionTo('login');
    }

    $scope.register = function() {
        $state.transitionTo('register');
    }
}]);