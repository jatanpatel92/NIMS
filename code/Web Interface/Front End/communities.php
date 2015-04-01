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
<?php require_once("includes/connection.php") ?>
<?php require_once("includes/functions.php") ?>
<?php require_once("includes/session.php") ?>
<?php confirm_logged_in(); ?>
<?php
	if(isset($_GET['com'])){
		$sel_com=$_GET['com'];
	} else { $sel_com=0; }
	$sel_community=get_one_community($sel_com);
?>
<script type="text/javascript" src="ofc/js/swfobject.js"></script>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>NIMS-Communities</title>
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

  <body>

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
	       <li class="active"><a href="communities.php">Communities</a></li>
	       <li><a href="coordinator.php">Coordinators</a></li>
	       <li><a href="projects.php">Projects</a></li>
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
        <div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header"><strong>Communities</strong></li><hr>
              <?php
			$result=get_all_communities();
			while($row=mysql_fetch_array($result)){
				echo "<li";
				if($row['com_id']==$sel_com){
					echo " class=\"active\"";
				}
				echo "><a href=\"communities.php?com=" . urlencode($row['com_id']).
				"\">{$row["com_name"]}</a></li>";
			}
	      ?>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span7">
          <div class="hero-unit">
            <h3>Graphical Analysis</h3><br /><br />
            
	    
	    <div id="pie_chart" ></div>
		<script type="text/javascript">
		if (!((<?php echo $sel_com ?>)==0)){
		var id=<?php echo $sel_com ?>;
		var url="charts/data.php?id="+id;
		}
		else{
			var url="charts/d1.php";
		}
		swfobject.embedSWF(
		  "ofc/open-flash-chart.swf", "pie_chart",
		  "600", "400", "9.0.0", "expressInstall.swf",
		  {"data-file":url} );
		$(document).ready(function(){
		$("#resize").resizable();
		});
		</script>
		
		<br /><br />
		<div id="pie_chart2"></div>
		<script type="text/javascript">
		if (!((<?php echo $sel_com ?>)==0)){
		var id=<?php echo $sel_com ?>;
		var url="charts/data2.php?id="+id;
		}
		else{
			var url="charts/d2.php";
		}
		swfobject.embedSWF(
		  "ofc/open-flash-chart.swf", "pie_chart2",
		  "600", "400", "9.0.0", "expressInstall.swf",
		  {"data-file":url} );
		$(document).ready(function(){
		$("#resize").resizable();
		});
		</script>
		
		<br /><br />
		<div id="pie_chart3"></div>
		<script type="text/javascript">
		if (!((<?php echo $sel_com ?>)==0)){
		var id=<?php echo $sel_com ?>;
		var url="charts/data3.php?id="+id;
		}
		else{
			var url="charts/d3.php";
		}
		swfobject.embedSWF(
		  "ofc/open-flash-chart.swf", "pie_chart3",
		 "600", "400", "9.0.0", "expressInstall.swf",
		  {"data-file":url} );
		$(document).ready(function(){
		$("#resize").resizable();
		});
		</script>
		
		<br /><br />
		<div id="pie_chart4"></div>
		<script type="text/javascript">
		if (!((<?php echo $sel_com ?>)==0)){
		var id=<?php echo $sel_com ?>;
		var url="charts/data4.php?id="+id;
		}
		else{
			var url="charts/d4.php";
		}
		swfobject.embedSWF(
		  "ofc/open-flash-chart.swf", "pie_chart4",
		  "600", "400", "9.0.0", "expressInstall.swf",
		  {"data-file":url} );
		$(document).ready(function(){
		$("#resize").resizable();
		});
		</script>
		
		<br /><br />
		<div id="pie_chart5"></div>
		<script type="text/javascript">
		if (!((<?php echo $sel_com ?>)==0)){
		var id=<?php echo $sel_com ?>;
		var url="charts/data5.php?id="+id;
		}
		else{
			var url="charts/d5.php";
		}
		swfobject.embedSWF(
		  "ofc/open-flash-chart.swf", "pie_chart5",
		  "600", "400", "9.0.0", "expressInstall.swf",
		  {"data-file":url} );
		$(document).ready(function(){
		$("#resize").resizable();
		});
		</script>
		
		<br /><br />
		<div id="pie_chart6"></div>
		<script type="text/javascript">
		if (!((<?php echo $sel_com ?>)==0)){
		var id=<?php echo $sel_com ?>;
		var url="charts/data6.php?id="+id;
		}
		else{
			var url="charts/d6.php";
		}
		swfobject.embedSWF(
		  "ofc/open-flash-chart.swf", "pie_chart6",
		  "600", "400", "9.0.0", "expressInstall.swf",
		  {"data-file":url} );
		$(document).ready(function(){
		$("#resize").resizable();
		});
		</script>
		
		<br /><br />
		<div id="pie_chart7"></div>
		<script type="text/javascript">
		if (!((<?php echo $sel_com ?>)==0)){
		var id=<?php echo $sel_com ?>;
		var url="charts/data7.php?id="+id;
		}
		else{
			var url="charts/d7.php";
		}
		swfobject.embedSWF(
		  "ofc/open-flash-chart.swf", "pie_chart7",
		  "600", "400", "9.0.0", "expressInstall.swf",
		  {"data-file":url} );
		$(document).ready(function(){
		$("#resize").resizable();
		});
		</script>
	    
          </div><!--/hero-unit-->
		 </div><!--/span-->
		  <div class="span3 offset8">
          <div class="hero-unit">
            <h3>Community Information</h3>    <hr> 
            <p><?php 
			if (!($sel_com==0)){
			echo "<strong>Community Name:</strong> " . "<br />" . $sel_community['com_name'] . "<br />". "<br />";
			$query="Select COUNT(*) FROM member_info natural join family_info  where com_id = " . $sel_community['com_id'];
			$pop=run_query($query);
			echo "<strong>Community Polpulation:</strong> " . "<br />" . $pop['COUNT(*)'] . "<br />" . "<br />";
			
			$query="select DISTINCT dist_name from family_info natural join settlement_info
			natural join village_info natural join taluka_info natural join district_info where com_id = " . $sel_community['com_id'];
			$result=run_query_2($query);
			echo "<strong>Districts Covered:</strong>" . "<br />" ;
			while($dist=mysql_fetch_array($result)){
				echo $dist['dist_name'] . ", ";
			}
			echo "<br />". "<br />";
			$query="Select com_id,  AVG(fam_daily_income) from family_info  where com_id = " . $sel_community['com_id'];
			$avg_inc=run_query($query);
			echo "<strong>Average Family Daily Income:</strong>" . "<br />"  . $avg_inc['AVG(fam_daily_income)'];
			}
		?></p>
          </div><!--/hero-unit-->
        </div><!--/span-->
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
