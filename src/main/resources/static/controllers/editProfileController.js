oasisApp.controller('editProfileController', ['$scope', '$state', 'loginService', function($scope, $state, loginService) {
    if (!loginService.isLoggedIn()) {
        $state.transitionTo('welcome');
    }
    $scope.alerts = []
    $scope.user = loginService.getUser();
    if ($scope.user.title == "") {
        $scope.user.title = "N/A";
    }
    $scope.userTitles = ["N/A", "Mr", "Mrs", "Dr"];
    $scope.setUserTitle = function (title) {
        user.title = title;
    }
    $scope.confirmPassword = "";

    $scope.submit = function () {
        if ($scope.confirmPassword != $scope.user.password) {
            $scope.alerts.push({type: 'danger', msg: 'Confirmed Password does not match.'});
        } else {
            loginService.updateUser().then( function (success) {
                $scope.alerts.push({type: 'success', msg: 'Updated Profile!'});
                $scope.user = success;
            },
            function(error) {
                $scope.alerts.push({type: 'danger', msg: error.data.message});
            })
        }
    }
    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };

    $scope.cancel = function() {
        $state.transitionTo('home');
    }
}]);