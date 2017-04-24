oasisApp.controller('loginController', ['$scope', '$state', 'loginService', function($scope, $state, loginService) {
    if (loginService.isLoggedIn()) {
        $state.transitionTo('home');
    }
    $scope.formSubmit = function () {
        loginService.attemptLogin($scope.username, $scope.password).then(function(success) {
            $state.transitionTo('home');
        },
        function(error) {
            console.log(error);
            $scope.alerts = [{'type': 'danger', 'msg': error.data.message}];
        });
    }

    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };
    $scope.setUserType = function(newUserType) {
        $scope.userType = newUserType;
    }

    $scope.cancel = function() {
        $state.transitionTo('welcome');
    }
}]);