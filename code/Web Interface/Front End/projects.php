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
<?php require_once("includes/functions.php") ?>
<?php require_once("includes/connection.php") ?>
<?php require_once("includes/session.php") ?>
<?php require_once("project_timeline.php") ?>
<?php confirm_logged_in(); ?>
<script src="http://simile.mit.edu/timeline/api/timeline-api.js" type="text/javascript"></script>
<!DOCTYPE html>
<html lang="en">
 <head>
    <meta charset="utf-8">
    <title>NIMS-Projects</title>
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

   <body onload="onLoad();" onresize="onResize();">

    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="home.php">NIMS</a>
          <div class="nav-collapse">
            <ul class="nav">
              <li><a href="home.php">Home</a></li>
	       <li><a href="communities.php">Communities</a></li>
	       <li class="active"><a href="projects.php">Projects</a></li>
	       <li><a href="data_tables.php">Tabular Data</a></li>
	      <li><a href="settings.php">Settings</a></li>
	      <li><a href="help.php">Help</a></li>
	      <li><a href="logout.php">Logout</a></li>
            </ul>
            <p class="navbar-text pull-right">Logged in as <a href="#"><?php echo $_SESSION['username']; ?></a></p>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        
        <div class="span15">
		  <div class="row-fluid">
          <div class="hero-unit">
            <h3>Project Timeline</h3><br>
	     <div id="my-timeline" style="height: 600px; width:1100px; border: 2px solid #aaa">
		 <?php create_xml(); ?>
		 
		  <script type="text/javascript">
		  var tl;
		  function onLoad() {
		  var eventSource = new Timeline.DefaultEventSource();
		  var theme = Timeline.ClassicTheme.create(); 
			 theme.ether.backgroundColors[0] = "#FFFFFF"; 
			 theme.ether.backgroundColors[1] = "#151B54"; 
			 theme.ether.backgroundColors[2] = "#FFFFFF"; 
		  var bandInfos = [
			Timeline.createBandInfo({
				eventSource:    eventSource,
				date:           "Dec 28 2009 00:00:00 GMT",
				width:          "70%", 
				intervalUnit:   Timeline.DateTime.MONTH, 
				theme: theme,
				intervalPixels: 100
			}),
			Timeline.createBandInfo({
				eventSource:    eventSource,
				date:           "Dec 28 2009 00:00:00 GMT",
				width:          "30%", 
				intervalUnit:   Timeline.DateTime.YEAR, 
				theme: theme,
				intervalPixels: 200
			})
		  ];
		  bandInfos[1].syncWith = 0;
		  bandInfos[1].highlight = true;
		  tl = Timeline.create(document.getElementById("my-timeline"), bandInfos);
		  Timeline.loadXML("event.xml",function(xml,url) { eventSource.loadXML(xml,url) } );
		}

		var resizeTimerID = null;
		function onResize() {
			if (resizeTimerID == null) {
				resizeTimerID = window.setTimeout(function() {
					resizeTimerID = null;
					tl.layout();
				}, 500);
			}
		}
		</script>
</div>
          </div><!--/hero-unit-->
		  </div><!--/row-->
		 
		</div><!--/span10-->
		  
      </div><!--/row-->
	  
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

  </body>
</html>
