oasisApp.factory('loginService', ['$state', '$http',
    function($state, $http) {

        var service = {};
        var userID = null;
        var user = null;

        service.isLoggedIn = function() {
            return userID != null;
        }

        service.attemptRegister = function(username, password, userType) {
            return $http.post('/api/user/create', {
                userName: username,
                password: password,
                userType: userType
            }).then(function (success) {
                user = success.data;
                userID = user.id;
            });
        }

        service.updateUser = function() {
            return $http.put('/api/user/' + user.id, user).then(function (success) {
                user = success.data;
                userID = user.id;
                return success.data;
            });
        }

        service.logout = function() {
            user = null;
            userID = null;
        }

        service.attemptLogin = function(username, password) {
            return $http.post('/api/user/login', {
                userName: username,
                password: password
            }).then(function (success) {
                user = success.data;
                userID = user.id;
            });
        }

        service.canViewWaterSources = function() {
            if (!user) return false;
            if (user.userType == "Reporter" || user.userType == "Worker" || user.userType == "Manager") return true;
            return false;
        }

        service.canSubmitWaterSources = function() {
            if (!user) return false;
            if (user.userType == "Reporter" || user.userType == "Worker" || user.userType == "Manager") return true;
            return false;
        }

        service.canSubmitQuality = function () {
            if (!user) return false;
            if (user.userType == "Worker" || user.userType == "Manager") return true;
            return false;
        }

        service.canViewSecurityLog = function () {
            if (!user) return false;
            if (user.userType == "Administrator") return true;
            return false;
        }
        service.canBlockUser = function () {
            if (!user) return false;
            if (user.userType == "Administrator") return true;
            return false;
        }


        service.setUserID = function (newUserID) {
            userID = newUserID;
        }

        service.getUserID = function () {
            return userID;
        }

        service.getUser = function () {
            return user;
        }

        return service;
}]);