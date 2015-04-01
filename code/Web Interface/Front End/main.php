<!--
Copyright 2012 “Team 16, Btech 2009 batch, DA-IICT”
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and  limitations under the License.
-->
<!DOCTYPE html>
<html lang="en">
  <head>
<?php
	if (isset($_GET['logout']) && $_GET['logout'] == 1) {
		$message = "<center><h4>You have successfully logged out.</h4></center>";
	}
?>
<?php require_once("includes/connection.php") ?>
<?php require_once("includes/functions.php") ?>
<?php require_once("includes/form_functions.php") ?>
<?php require_once("includes/session.php") ?>
<?php
	function log_in(){
		return isset($_SESSION['username']);
	}
	function confirm_log_in(){
		if(log_in()){
		redirect_to("home.php");
    }
}
?>
<?php confirm_log_in(); ?>
  
    <meta charset="utf-8">
    <title>NIMS-Main</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
	      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
    

      #basicMap {
          width: 1300px;
          height: 500px;
          margin: 0;
      }

      .sidebar-nav {
        padding: 9px 0;
      }

    </style>
    <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="images/favicon.ico">
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="images/apple-touch-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="images/apple-touch-icon-114x114.png">
  </head>

  <body >
       <?php
		$fp= fopen("xml/test.txt", "w");
	    $query="SELECT * from socialmap";
        $result=mysql_query($query, $connection);
        confirm_query($result);
		while($row=mysql_fetch_array($result)){
			$lat=$row['latitude'];
			$lon=$row['longitude'];
			$typ=$row['type'];
			$nam=$row['Name'];
			$vil=$row['village'];
			$a=$lat . "\n" . $lon . "\n" . $typ . "\n" . $nam . "\n" . $vil . "\n";
			fwrite($fp,$a);
		}
	?>
	<?php if (!empty($message)) {echo "<p class=\"message\">"  . $message  . "</p>";} ?>
	
		<center><h3>Select the district </h3>
		
        <select id="jump" onchange="jump();">
            <option value="72.628112793,23.1886348724,7">=== Select district ===</option>
            <option value="70.7800,22.3000,11">Rajkot</option>
			<option value="72.3918352,23.5976589,11">Mahesana</option>
			<option value="72.1191325,23.8507063,11">Patan</option>
			<option value="72.13717,24.3964887,10">Banaskantha</option>
			<option value="73.2633572,23.9772603,11">Sabarkantha</option>
            <option value="72.6800,23.2200,12">gandhinagar</option>  
            <option value="72.6167,23.0333,11">Ahmedabad</option>
        </select></center>

    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="main.php">NGO INFORMATION MANAGEMENT SUITE</a>

		  <ul class="nav js-global-actions">
			<li class="home" data-global-action="t1home">
            <a href="/">
              <span><i class="nav-home-logged-out"></i></span>
            </a>
			</li>
          </ul>
          <ul class="nav pull-right">
			<!--<li class="dropdown">
			<!--<a href="#" class="dropdown-toggle dropwdown-signin" data-toggle="dropdown">-->
			<a class="btn " href="login.php" data-toggle="collapse" data-target=".nav-collapse ">             
			 <b> <font color="#E66C2C">Log In </font></b>
			  
            </a>
							
        </div>
      </div>
    </div>
<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">

			</div><!--/span12-->
		</div><!--/row-fluid-->
		<div class="row-fluid">
			<div class="span10">
     <div id="basicMap"> </div>
          </div><!--/.well -->
      
   </div><!--/.fluid-container-->
<hr>
      <footer>
        <p>Code licensed under <a href="http://www.apache.org/licenses/LICENSE-2.0.html">Apache License 2.0</a></p>
		<p>Powered by <a href="https://bitbucket.org/SkishChampi/nims-s">NIMS 1.0</a></p>
      </footer>

    </div><!--/.fluid-container-->
    
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap-transition.js"></script>
    <script src="assets/js/bootstrap-alert.js"></script>
    <script src="assets/js/bootstrap-modal.js"></script>
    <script src="assets/js/bootstrap-dropdown.js"></script>
    <script src="assets/js/bootstrap-scrollspy.js"></script>
    <script src="assets/js/bootstrap-tab.js"></script>
    <script src="assets/js/bootstrap-tooltip.js"></script>
    <script src="assets/js/bootstrap-popover.js"></script>
    <script src="assets/js/bootstrap-button.js"></script>
    <script src="assets/js/bootstrap-collapse.js"></script>
    <script src="assets/js/bootstrap-carousel.js"></script>
    <script src="assets/js/bootstrap-typeahead.js"></script>
	<script src="http://www.openlayers.org/api/OpenLayers.js"></script>
    <script src="http://maps.google.com/maps/api/js?v=3.2&sensor=false"></script>
    <script src="map2.js"></script>


  </body>
</html>
