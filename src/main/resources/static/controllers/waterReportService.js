oasisApp.factory('waterReportService', ['$state', '$http', function($state, $http) {
    var service = {};

    service.getWaterSources = function () {
        return $http.get('/api/sourcereports').then(function(success) {
            return success.data;
        });
    }

    service.getWaterQualities = function () {
        return $http.get('/api/qualityreports').then(function(success) {
            return success.data;
        });
    }

    service.saveWaterSource = function (waterSource) {
        return $http.post('/api/sourcereports/create', waterSource).then(function(success) {
            return success.data;
        });
    }

    service.saveWaterQuality= function (waterQuality) {
        return $http.post('/api/qualityreports/create', waterQuality).then(function(success) {
            return success.data;
        });
    }

    service.reportToString = function(report) {
        return "<p>Source Report<br>Report Number: " + report.id  + "<br> Reporter Name: " + report.reporterName + "<br>Type: + " + report.waterType + "<br>Condition: " + report.waterCondition + "<br>Lat: " + report.latitude + "<br>Lng: " + report.longitude + "</p>"
    }

    service.qualityToString = function(report) {
        return "<p>Purity Report<br>Report Number: " + report.id +  "<br> Reporter Name: " + report.reporterName + "<br>Overall Condition:" + report.overallCondition + "<br>Virus PPM: " + report.virusPPM + "<br>ContaminantsPPM: " + report.contaminantPPM + "<br>Lat: " + report.latitude + "<br>Lng: " + report.longitude + "</p>"
    }

    return service;
}]);