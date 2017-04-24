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