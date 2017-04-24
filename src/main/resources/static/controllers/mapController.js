oasisApp.controller('mapController', ['$scope', '$state', 'loginService', 'waterReportService', function($scope, $state, loginService, waterReportService) {
    if (!loginService.isLoggedIn()) {
        $state.transitionTo('welcome');
    }
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

        waterReportService.getWaterQualities().then(function(reports) {

                    reports.forEach(function (report) {
                        var latlng = L.latLng(report.latitude, report.longitude);
                        var marker = L.marker(latlng, {
                            title: report.waterType,
                            riseOnHover: true
                        })
                        marker.report = report;
                        marker.addTo(map).bindPopup(waterReportService.qualityToString(report));
                    });
        });

        $scope.back = function() {
            $state.transitionTo("home");
        }
        function onLocationError(e) {
            alert(e.message);
        }

        map.locate({setView: true, maxZoom: 16});
        map.on('locationerror', onLocationError);

	 	//map.on('click', onMapClick);
}]);