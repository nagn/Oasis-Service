oasisApp.controller('logController', ['$scope', '$http', function($scope, $http) {
    $http.get('api/logs').then(function (success) {
        console.log(success);
        $scope.logs = success.data;
    });
}]);

