     /*
	Copyright 2012 "Team 16, BTech 2009 Batch, DA-IICT"

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
	*/	 
        window.onload=init;
		/* function to implement map feature and pop feature*/
        function init() {
			var filePath = "xml/charts.txt";
			xmlhttp = new XMLHttpRequest();
			xmlhttp.open("GET","xml/test.txt",false);
			xmlhttp.send(null);
			var fileContent = xmlhttp.responseText;
			var fileArray = fileContent.split('\n');
			var latitude=new Array();
			var longitude=new Array();
			var type=new Array();
			var name=new Array();
			var vil=new Array();
			var i=0;
			var j=0;
			for(j=0;j<fileArray.length;j++){
				latitude[i]=fileArray[j];
				j++;
				longitude[i]=fileArray[j];
				j++;
				type[i]=fileArray[j];
				j++;
				name[i]=fileArray[j];
				j++;
				vil[i]=fileArray[j];
				i++;		
		}	
		map = new OpenLayers.Map("basicMap");
		//var mapnik = new OpenLayers.Layer.OSM();
		var mapnik = new OpenLayers.Layer.Google("Google Streets",{numZoomLevels: 20});
		map.addLayer(mapnik);
		var lonLat = new OpenLayers.LonLat( 72.628112793 , 23.1886348724 )
          .transform(
            new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
            new OpenLayers.Projection("EPSG:900913") // to Spherical Mercator Projection
         );
		map.setCenter(lonLat, 7);// Zoom level
		var layer = new OpenLayers.Layer.Vector("Polygon", {
               strategies: [new OpenLayers.Strategy.Fixed()],
               protocol: new OpenLayers.Protocol.HTTP({
               url: "kamod.osm",   //<-- relative or absolute URL to your .osm file
               format: new OpenLayers.Format.OSM()
               }),
               projection: new OpenLayers.Projection("EPSG:4326")
            }
		);
		map.addLayers([layer]);
		var layer = new OpenLayers.Layer.Vector("Polygon", {
              strategies: [new OpenLayers.Strategy.Fixed()],
              protocol: new OpenLayers.Protocol.HTTP({
              url: "lp.xml",   //<-- relative or absolute URL to your .osm file
              format: new OpenLayers.Format.OSM()
              }),
              projection: new OpenLayers.Projection("EPSG:4326")
              }
		);
        map.addLayers([layer]);
		var vectorLayer = new OpenLayers.Layer.Vector("Overlay");
		for(j=0;j<latitude.length;j++){
			var a= createfeature(longitude[j] , latitude[j],type[j],name[j],vil[j]);
			vectorLayer.addFeatures(a);
		}

		var controls = {
			selector: new OpenLayers.Control.SelectFeature(vectorLayer, { onSelect: createPopup, onUnselect: destroyPopup })
		};
		function createfeature(x,y,z,w,q){
			var feature = new OpenLayers.Feature.Vector(
            new OpenLayers.Geometry.Point( x, y ).transform(new OpenLayers.Projection("EPSG:4326"), new OpenLayers.Projection("EPSG:900913")),
            {description: '<b>LOCATION:</b></br><font color="blue">Village:</font>'+q+'</br><font color="blue">Longitude:</font>'+x+ '</br> <font color="blue">Latitude:</font>'+y+'</br><b>DESCRIPTION:</b></br><font color="blue">Type:</font>'+z+ '</br><font color="blue">Name:</font>'+w} ,
            {externalGraphic: 'symbols/'+z+'.png', graphicHeight: 18, graphicWidth: 22, graphicXOffset:-12, graphicYOffset:-25  }
			);  
			return feature;
		}
		function createPopup(feature) {
			feature.popup = new OpenLayers.Popup.FramedCloud("pop",
			feature.geometry.getBounds().getCenterLonLat(),
			null,
			'<div class="markerContent">'+feature.attributes.description+'</div>',
			null,
			true,
			function() { controls['selector'].unselectAll(); }
			);
			map.addPopup(feature.popup);
		}
		function destroyPopup(feature) {
			feature.popup.destroy();
			feature.popup = null;
		}
		map.addControl(controls['selector']);
		controls['selector'].activate();
		map.addLayer(vectorLayer);
		}
	  	
	function lnlt( x ,  y){
		var lonLat = new OpenLayers.LonLat(x , y )
        .transform(
         new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
         new OpenLayers.Projection("EPSG:900913") // to Spherical Mercator Projection
         );
		return lonLat;
	}
	  
	function icn(x){
	  	var size = new OpenLayers.Size(21,25);
		var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
		var icon = new OpenLayers.Icon(x, size, offset);
		return icon;
	}
	
	/*function to implement dropdown menu of select district when new district selected*/
	function jump(){
		var selectBox = document.getElementById('jump');        		
		if (selectBox.value == "NA") return;
		var parse = selectBox.value.split(',');
			var lonLat = new OpenLayers.LonLat(  parse[0], parse[1] )
            .transform(
            new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
            new OpenLayers.Projection("EPSG:900913") // to Spherical Mercator Projection
          );
		map.setCenter(lonLat, parse[2]);
	  }
	
  