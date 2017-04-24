oasisApp.controller('createSourceReportController', ['$scope', '$state', 'loginService', 'waterReportService',

function($scope, $state, loginService, waterReportService) {
    $scope.back = function () {
        $state.transitionTo('home');
    }
    if (!loginService.isLoggedIn()) {
        $state.transitionTo('welcome');
        return;
    }

    $scope.alerts = []

    $scope.newReport = {
        latitude: 0.0,
        longitude: 0.0,
        waterCondition: "Waste",
        waterType: "Bottled",
        reporterName: loginService.getUser().fullName,
        timestamp: Math.round(new Date().getTime() / 1000)
    };


    $scope.waterConditions = ['Waste', 'Treatable-Clear', 'Treatable-Muddy', 'Potable']
    $scope.waterTypes = ['Bottled', 'Well', 'Stream', 'Lake','Spring', 'Other']
    var marker;

    // We’ll add a tile layer to add to our map, in this case it’s a OSM tile layer.
    // Creating a tile layer usually involves setting the URL template for the tile images
    var osmUrl = 'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
        osmAttrib = '&copy; <a href="http://openstreetmap.org/copyright">OpenStreetMap</a> contributors',
        osm = L.tileLayer(osmUrl, {
        maxZoom: 18,
        attribution: osmAttrib
    });

    // initialize the map on the "map" div with a given center and zoom
    var map = L.map('map').fitWorld().addLayer(osm);

    function onLocationError(e) {
        alert(e.message);
    }

    // Script for adding marker on map click
    var scope = $scope;
    function updateMarker(e) {
        scope.$apply(function() {
            $scope.newReport.latitude = e.latlng.lat;
            $scope.newReport.longitude = e.latlng.lng;
        });
        if (!marker) {
            marker = L.marker(e.latlng, {
                draggable: true,
                title: "Resource location",
                alt: "Resource Location",
                riseOnHover: true
            });
            // Update marker on changing it's position
            marker.on("dragend", function (ev) {
                var changedPos = ev.target.getLatLng();
                $scope.updateLatLng(changedPos.lat, changedPos.lng);
                this.bindPopup(changedPos.toString()).openPopup();
            });
            marker.addTo(map).bindPopup(e.latlng.toString()).openPopup();
        } else {
            marker.setLatLng(e.latlng);
            marker.bindPopup(e.latlng.toString()).openPopup();
        }
    }

    waterReportService.getWaterSources().then(function(reports) {
                reports.forEach(function (report) {
                    var latlng = L.latLng(report.latitude, report.longitude);
                    var marker = L.marker(latlng, {
                        title: report.waterType,
                        alt: "Condition: " + report.waterCondition,
                        riseOnHover: true
                    })
                    marker.report = report;
                    marker.addTo(map).bindPopup(waterReportService.reportToString(report));
                });
    });
    function onMapClick(e) {
        updateMarker(e);
    }

    function onLocationFound(e) {
        updateMarker(e);
    }

    $scope.changeLatLng = function () {
        marker.setLatLng(L.latLng($scope.newReport.latitude, $scope.newReport.longitude));
    }

    $scope.updateLatLng = function (lat, lng) {
        scope.$apply(function() {
            $scope.newReport.latitude = lat;
            $scope.newReport.longitude = lng;
        });
    }

    $scope.save = function () {
        waterReportService.saveWaterSource($scope.newReport).then(function (success) {
            $scope.alerts.push({type: 'success', msg: 'Successfully Added the new report!'});
        },
        function(error) {
            $scope.alerts.push({type: 'danger', msg: error.data.message});
        });

        marker = null;

        $scope.newReport = {
                latitude: 0.0,
                longitude: 0.0,
                waterCondition: "Waste",
                waterType: "Bottled",
                reporterName: loginService.getUser().fullName,
                timestamp: Math.round(new Date().getTime() / 1000)
            };
    }
     $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };

    map.locate({setView: true, maxZoom: 16});
    map.on('locationerror', onLocationError);
    map.on('locationfound', onLocationFound)
    map.on('click', onMapClick);

}]);
