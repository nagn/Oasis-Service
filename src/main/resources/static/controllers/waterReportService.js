oasisApp.factory('waterReportService', ['$state', '$http', function($state, $http) {
    var service = {};

    service.getWaterSources = function () {
        return $http.get('/api/sourcereports').then(function(success) {
            return success.data;
        });
    }

    service.reportToString = function(report) {
        return "<p>Type:" + report.waterType + "<br>Condition: " + report.waterCondition + "<br>Lat: " + report.latitude + "<br>Lng: " + report.longitude + "</p>"
    }

    return service;
}]);