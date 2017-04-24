oasisApp.controller('registerController', ['$scope', '$state', 'loginService', function($scope, $state, loginService) {
    if (loginService.isLoggedIn()) {
        $state.transitionTo('home');
    }
    $scope.alerts = [];
    $scope.userType = "Reporter";
    $scope.userTypes = ["Reporter", "Worker", "Manager", "Administrator"];
    $scope.formSubmit = function() {
        loginService.attemptRegister($scope.username, $scope.password, $scope.userType).then(function(success) {
            $state.transitionTo('home');
        },
        function(error) {
            console.log(error);
            $scope.alerts = [{type: 'danger', msg: error.data.message}];
        });
    }
    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };
    $scope.setUserType = function(newUserType) {
        $scope.userType = newUserType;
    }
    $scope.cancel = function() {
        // User doesn't want to register, return to welcome screen
        $state.transitionTo('welcome');
    }
}]);