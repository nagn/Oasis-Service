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

        function onLocationError(e) {
            alert(e.message);
        }

	 	// Script for adding marker on map click
	 	/*
	 	function onMapClick(e) {
	 	    var marker = L.marker(e.latlng, {
	 	        draggable: true,
	 	        title: "Resource location",
	 	        alt: "Resource Location",
	 	        riseOnHover: true
	 	    }).addTo(map)
	 	        .bindPopup(e.latlng.toString()).openPopup();

	 	    // Update marker on changing it's position
	 	    marker.on("dragend", function (ev) {

	 	        //var chagedPos = ev.target.getLatLng();
	 	        //this.bindPopup(chagedPos.toString()).openPopup();

	 	    });
	 	}
	 	*/
	 	map.locate({setView: true, maxZoom: 16});
	    map.on('locationerror', onLocationError);

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

        $scope.back = function() {
            $state.transitionTo("home");
        }
	 	//map.on('click', onMapClick);
}]);