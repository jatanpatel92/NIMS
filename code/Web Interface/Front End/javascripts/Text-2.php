<!DOCTYPE HTML>
<html>
           
<head>
 <title>ass</title>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?v=3.2&sensor=false"></script>
<script type="text/javascript" src="http://openlayers.org/api/OpenLayers.js" ></script>

<script>
            map = new OpenLayers.Map('map');            
            baseLayer = new OpenLayers.Layer.Google("Google Streets", {numZoomLevels: 20});
            map.addLayer(baseLayer);
            map.setCenter(new OpenLayers.LonLat(-98, 39),3); 
</script>

</head>
<body>
<div id="map">

</div>
</body>
<style>
    @media screen
    {
        #map{width: 500px; height:300px; border: 2px solid black;}
    }
    </style>
</html>