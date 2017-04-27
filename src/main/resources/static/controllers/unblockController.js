oasisApp.controller('unblockController', ['$scope', '$http', 'loginService', function($scope, $http, loginService) {
    $scope.userID;
    $scope.alerts = [];
    $scope.unblockUser = function() {
        $http.get('api/block/' + $scope.userID).then(function(success) {
            console.log(success);
            $scope.alerts.push({type: 'success', msg: 'Successfully unblocked ' +$scope. userID});
        }, function (error) {
            $scope.alerts.push({type: 'danger', msg: error.data.message});
        })
    }
    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };

}]);