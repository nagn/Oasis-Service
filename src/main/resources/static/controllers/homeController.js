oasisApp.controller('homeController', ['$scope', '$state',  'loginService', function($scope, $state, loginService) {

    if (!loginService.isLoggedIn()) {
        $state.transitionTo('welcome');
    }
    $scope.user = loginService.getUser();

    $scope.editProfileClick = function() {
        $state.transitionTo('edit-profile');
    }
    $scope.logout = function () {
        loginService.logout();
        $state.transitionTo('welcome');
    }
}]);