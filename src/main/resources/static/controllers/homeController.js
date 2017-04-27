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
    $scope.viewWaterSources = function () {
        $state.transitionTo('map');
    }
    $scope.submitWaterSourceReport = function() {
        $state.transitionTo('create-source-report');
    }
    $scope.submitWaterQualityReport = function() {
        $state.transitionTo('create-quality-report');
    }
    $scope.viewSecurityLog = function() {
        $state.transitionTo('security-log');
    }
    $scope.canViewWaterSources = loginService.canViewWaterSources;
    $scope.canSubmitWaterSources = loginService.canSubmitWaterSources;
    $scope.canSubmitWaterQuality = loginService.canSubmitQuality;
    $scope.canViewSecurityLog = loginService.canViewSecurityLog;

}]);