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
    <meta charset="utf-8">
    <title>NIMS-Tabular Data</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<?php require_once("includes/functions.php") ?>
	<?php require_once("includes/connection.php") ?>
	<?php require_once("includes/session.php") ?>
    <?php confirm_logged_in(); ?>
	<?php
	$dist=0;
	$tal=0;
	$vil=0;
	$set=0;
	$fam=0;
	$tab_id=0;
	$nav="";
	$item_id=0;
	
	if(isset($_GET['id'])) {
		$tab_id=$_GET['id'];
	} else if(isset($_GET['dist'])) {
		$dist=$_GET['dist'];
		$nav="District &mdash; "."<a href=\"data_tables.php?id=3\">Taluka</a>";
	} else if(isset($_GET['tal'])) {
		$tal=$_GET['tal'];
		$nav="District &mdash; Taluka &mdash; "."<a href=\"data_tables.php?id=2\">Village</a>";
	} else if(isset($_GET['vil'])) {
		$vil=$_GET['vil'];
		$nav="District &mdash; Taluka &mdash; Village &mdash; "."<a href=\"data_tables.php?id=1\">Settlement</a>";
	} else if(isset($_GET['set'])) {
		$set=$_GET['set'];
		$nav="District &mdash; Taluka &mdash; Village &mdash; Settlement &mdash; "."<a href=\"data_tables.php?id=5\">Family</a>";
	} else if(isset($_GET['fam'])) {
		$fam=$_GET['fam'];
		$nav="District &mdash; Taluka &mdash; Village &mdash; Settlement &mdash; Family &mdash; "."<a href=\"data_tables.php?id=6\">Member</a>";
	}elseif(isset($_GET['item_id'])) {
		$item_id=$_GET['item_id'];
		$nav="Item info &mdash; <a>Item Distribution</a>";
	} else {
		$tab_id=4;
	}
?>
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
	       <li><a href="communities.php">Communities</a></li>
	      <li><a href="projects.php">Projects</a></li>
	       <li class="active"><a href="data_tables.php">Tabular Data</a></li>
	      <li ><a href="settings.php">Settings</a></li>
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
        <div class="span3">
          <div class="well sidebar-nav">
		   <ul class="nav nav-list">
              <li class="nav-header"><strong>Tables</strong></li><hr>
              <li><a href="data_tables.php?id=4">District Information</a>
			  <li><a href="data_tables.php?id=3">Taluka Information</a>
			  <li><a href="data_tables.php?id=2">Village Information</a>
			  <li><a href="data_tables.php?id=1">Settlement Information</a>
			<li><a href="data_tables.php?id=5">Family Information</a>
			<li><a href="data_tables.php?id=6">Member Information</a><hr>
			<li><a href="data_tables.php?id=7">Items Information</a>
			<li><a href="data_tables.php?id=8">Item Distribution</a>
		
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span9">
          <h4><?php echo $nav ?></h4><br /><br />  
		  <?php 
						//Display settlement information in tabular form
						if($tab_id==1) {
							echo "<b><font size='5'>" . "Settlement Information" . "</font>" . "<br />" . "<br />";
							$result = mysql_query("SELECT * from settlement_info natural join village_info 
							natural join taluka_info natural join district_info");
							echo "<table class=\"table table-bordered table-striped\">";
							echo "<thead>";
							echo "<tr><th>Settlement Name</th><th>Village Name</th><th>Taluka Name</th><th>District Name</th></tr>";
							echo "</thead>";
							echo "<tbody>";
							while($row = mysql_fetch_array($result))
							{
								//Display the results in different cells
								echo "<tr><td><a href=\"data_tables.php?set=".$row["set_id"].
								"\">{$row["set_name"]}</a></td><td>" . $row['vil_name'] . "</td><td>" . $row['taluka_name'] . "</td><td>" . $row['dist_name'] . "</td></tr>";
							}
							//Table closing tag
							echo "</tbody>";
							echo "</table>";
						}
						//Display village information in tabular form
						else if($tab_id==2) {
							echo "<b><font size='5'>" . "Village Information" . "</font>" . "<br />" . "<br />";
							$result = mysql_query("SELECT * from village_info natural join taluka_info natural join district_info");
							echo "<table class=\"table table-bordered table-striped\">
							<thead><tr><th>Village Name</th><th>Taluka Name</th><th>District Name</th></tr></thead>";
							echo "<tbody>";
							while($row = mysql_fetch_array($result)){
							//Display the results in different cells
							echo "<tr><td><a href=\"data_tables.php?vil=".$row["vil_id"].
								"\">{$row["vil_name"]}</a></td><td>" . $row['taluka_name'] . "</td><td>" . $row['dist_name'] . "</td></tr>";
						}
						//Table closing tag
							echo "</tbody></table>";
						}
						//Display taluka information in tabular form
						else if($tab_id==3) {
							echo "<b><font size='5'>" . "Taluka Information" . "</font>" . "<br />" . "<br />";
							$result = mysql_query("SELECT * from taluka_info natural join district_info");
							echo "<table class=\"table table-bordered table-striped\">
							<thead><tr><th>Taluka Name</th><th>District Name</th></tr></thead>";
							echo "<tbody>";
							while($row = mysql_fetch_array($result))
							{
								//Display the results in different cells
								echo "<tr><td><a href=\"data_tables.php?tal=".$row["taluka_id"].
								"\">{$row["taluka_name"]}</a></td><td>" . $row['dist_name'] . "</td></tr>";
							}
								//Table closing tag
								echo "</tbody></table>";	
						}
						//Display district information in tabular form
						else if($tab_id==4) {
							echo "<b><font size='5'>" . "District Information" . "</font>" . "<br />" . "<br />";
							$result = mysql_query("SELECT * from district_info");
							echo "<table class=\"table table-bordered table-striped\">
							<thead><tr><th>District Name</th></tr></thead>";
							echo "<tbody>";
							while($row = mysql_fetch_array($result))
							{
								//Display the results in different cells
								echo "<tr><td><a href=\"data_tables.php?dist=".$row["dist_id"].
								"\">{$row["dist_name"]}</a></td></tr>";
							}
							//Table closing tag
							echo "</tbody></table>"; 
							
						}
						//Display family information in tabular form
						else if($tab_id==5) {
							echo "<b><font size='5'>" . "Family Information" . "</font>" . "<br />" . "<br />";
							$result = mysql_query("SELECT * from family_info natural join community_info
							natural join settlement_info order by com_name");
							echo "<table class=\"table table-bordered table-striped\">
							<thead><tr><th>Community Name</th><th>Settlement Name</th><th>Family Head</th><th>No. of members</th><th>No. of children</th><th>Last Migrated From</th><th>Traditional Occupation</th><th>Daily Income</th><th>Ration Card Status</th><th>Ration Card Category</th><th>Electricity Status</th><th>No. of handicapped</th><th>Janani Support Status</th><th>Loan Application Status</th><th>Water Connection</th><th>Pension Scheme For Old</th><th>Plot Card Status</th><th>No. of children in school</th><th>Settlement Date</th><th>Housing Support</th><th>Widow Pension Scheme</th></tr></thead>";
							echo "<tbody>";
							while($row = mysql_fetch_array($result))
							{
								//Display the results in different cells
								echo "<tr><td>".$row["com_name"]."</td><td>" . $row['set_name'] . "</td><td><a href=\"data_tables.php?fam=".$row["fam_id"].
								"\">{$row["fam_head"]}</a></td><td>" . $row['fam_no_of_members'] . "</td><td>" . $row['fam_no_of_children'] . "</td><td>" . $row['fam_last_migrated_from'] . "</td><td>" . $row['fam_traditional_occupation'] . "</td><td>" . $row['fam_daily_income'] . "</td><td>" . $row['fam_ration_card_status'] . "</td><td>" . $row['fam_ration_card_category'] . "</td><td>" . $row['fam_electricity_status'] . "</td><td>" . $row['fam_no_of_handicapped'] . "</td><td>" . $row['fam_janani_support_status'] . "</td><td>" . $row['fam_loan_application_status'] . "</td><td>" . $row['fam_water_connection'] . "</td><td>" . $row['fam_vraddh_pen_scheme'] . "</td><td>" . $row['Fam_plot_card_Status'] . "</td><td>" . $row['Fam_no_of_children_school'] . "</td><td>" . $row['Fam_settlement_date'] . "</td><td>" . $row['Fam_housing_support'] . "</td><td>" . $row['Fam_widow_pension_scheme'] . "</td></tr>";
							}
							//Table closing tag
							echo "</tbody></table>"; 			
						}
						//Display member information in tabular form
						else if($tab_id==6) {
							echo "<b><font size='5'>" . "Members Information" . "</font>" . "<br />" . "<br />";
							$result=mysql_query("Select * from member_info natural join family_info");
							echo "<table class=\"table table-bordered table-striped\">
							<thead><tr><th>Family Head</th><th>Member Name</th><th>Birthyear</th><th>Occupation</th><th>Relation with Head</th><th>Gender</th><th>Job Card Status</th><th>Voter Card Status</th><th>Aadhar Card</th></tr></thead>";
							echo "<tbody>";
							while($row = mysql_fetch_array($result))
							{
								//Display the results in different cells
								echo "<tr><td>" . $row['fam_head'] . "</td><td>" . $row['mem_name'] . "</td><td>" . $row['mem_birthyear']. "</td><td>" . $row['mem_occupation'] . "</td><td>" . $row['mem_relationwithead'] . "</td><td>" . $row['mem_gender'] . "</td><td>" . $row['mem_jobcard_status'] . "</td><td>" . $row['mem_voterstatus'] . "</td><td>" . $row['mem_aadhar_status'] . "</td></tr>";
							}
							//Table closing tag
							echo "</tbody></table>"; 
						}
						//Display items information in tabular form
						else if($tab_id==7) {
							echo "<b><font size='5'>" . "Items Information" . "</font>" . "<br />" . "<br />";
							$result=mysql_query("Select * from item_info");
							echo "<table class=\"table table-bordered table-striped\">
							<thead><tr><th>Item Name</th></tr></thead>";
							echo "<tbody>";
							while($row = mysql_fetch_array($result))
							{
								//Display the results in different cells
								echo "<tr><td><a href=\"data_tables.php?item_id=".$row["item_id"]."\">{$row['item_name']}</a></td></tr>";
							}
							//Table closing tag
							echo "</tbody></table>"; 
						}
						//Display item distribution information in tabular form
						else if($tab_id==8) {
							echo "<b><font size='5'>" . "Item Distribution" . "</font>" . "<br />" . "<br />";
							$result=mysql_query("Select * from item_distribution");
							echo "<table class=\"table table-bordered table-striped\">
							<thead><tr><th>Item Name</th><th>Family Head</th><th>Number of Items</th><th>Settlement Name</th>
							<th>Coordinator Name</th></tr></thead>";
							echo "<tbody>";
							while($row = mysql_fetch_array($result))
							{
								$query="Select * from item_info where item_id=".$row['item_id'];
								$result1 = mysql_query($query, $connection);
								$row1=mysql_fetch_array($result1);
								$item_name=$row1['item_name'];
								$query="Select * from family_info where fam_id=".$row['fam_id'];
								$result2 = mysql_query($query, $connection);
								$row2=mysql_fetch_array($result2);
								$fam_name=$row2['fam_head'];
								$query="Select * from coordinator_info where coord_id=".$row['coord_id'];
								$result3 = mysql_query($query, $connection);
								$row3=mysql_fetch_array($result3);
								$coord_name=$row3['coord_name'];
								$query="Select * from settlement_info where set_id=".$row['set_id'];
								$result4 = mysql_query($query, $connection);
								$row4=mysql_fetch_array($result4);
								$set_name=$row4['set_name'];
								echo "<tr><td>$item_name</td><td>".$fam_name."</td><td>".$row['no_of_items']."</td><td>".$set_name.
								"<td>".$coord_name."</td></tr>";
							}
							//Table closing tag
							echo "</tbody></table>"; 
						}
						//Display taluka information of a particular district in tabular form
						else if(!($dist==0)) {
							echo "<b><font size='5'>" . "Taluka Information" . "</font>" . "<br />" . "<br />";
							$result = mysql_query("SELECT * from taluka_info natural join district_info
							where dist_id=".$dist);
							echo "<table class=\"table table-bordered table-striped\">
							<thead><tr><th>Taluka Name</th><th>District Name</th></tr></thead>";
							echo "<tbody>";
							while($row = mysql_fetch_array($result))
							{
								//Display the results in different cells
								echo "<tr><td><a href=\"data_tables.php?tal=".$row["taluka_id"].
								"\">{$row["taluka_name"]}</a></td><td>" . $row['dist_name'] . "</td></tr>";
							}
								//Table closing tag
								echo "</tbody></table>";	
						}
						//Display village information of a particular taluka in tabular form
						else if(!($tal==0)) {
							echo "<b><font size='5'>" . "Village Information" . "</font>" . "<br />" . "<br />";
							$result = mysql_query("SELECT * from village_info natural join taluka_info 
							natural join district_info where taluka_id=".$tal);
							echo "<table class=\"table table-bordered table-striped\">
							<thead><tr><th>Village Name</th><th>Taluka Name</th><th>District Name</th></tr></thead>";
							echo "<tbody>";
							while($row = mysql_fetch_array($result)){
							//Display the results in different cells
							echo "<tr><td><a href=\"data_tables.php?vil=".$row["vil_id"].
								"\">{$row["vil_name"]}</a></td><td>" . $row['taluka_name'] . "</td><td>" . $row['dist_name'] . "</td></tr>";
						}
						//Table closing tag
								echo "</body></table>";	
						}
						//Display settlement information of a particular village in tabular form
						if(!($vil==0)) {
							echo "<b><font size='5'>" . "Settlement Information" . "</font>" . "<br />" . "<br />";
							$result = mysql_query("SELECT * from settlement_info natural join village_info 
							natural join taluka_info natural join district_info where vil_id=".$vil);
							echo "<table class=\"table table-bordered table-striped\">
							<thead><tr><th>Settlement Name</th><th>Village Name</th><th>Taluka Name</th><th>District Name</th></tr></thead>";
							echo "<tbody>";
							while($row = mysql_fetch_array($result))
							{
								//Display the results in different cells
								echo "<tr><td><a href=\"data_tables.php?set=".$row["set_id"].
								"\">{$row["set_name"]}</a></td><td>" . $row['vil_name'] . "</td><td>" . $row['taluka_name'] . "</td><td>" . $row['dist_name'] . "</td></tr>";
							}
							//Table closing tag
							echo "</tbody></table>";
						}
						//Display family information of a particular settlement in tabular form
						else if(!($set==0)) {
							echo "<b><font size='5'>" . "Family Information" . "</font>" . "<br />" . "<br />";
							$result = mysql_query("SELECT * from family_info natural join community_info
							natural join settlement_info where set_id=".$set." order by com_name");
							echo "<table class=\"table table-bordered table-striped\">
							<thead><tr><th>Community Name</th><th>Settlement Name</th><th>Family Head</th><th>No. of members</th><th>No. of children</th><th>Last Migrated From</th><th>Traditional Occupation</th><th>Daily Income</th><th>Ration Card Status</th><th>Ration Card Category</th><th>Electricity Status</th><th>No. of handicapped</th><th>Janani Support Status</th><th>Loan Application Status</th><th>Water Connection</th><th>Pension Scheme For Old</th><th>Plot Card Status</th><th>No. of children in school</th><th>Settlement Date</th><th>Housing Support</th><th>Widow Pension Scheme</th></tr></thead>";
							echo "<tbody>";
							while($row = mysql_fetch_array($result))
							{
								//Display the results in different cells
								echo "<tr><td>".$row["com_name"]."</td><td>" . $row['set_name'] . "</td><td><a href=\"data_tables.php?fam=".$row["fam_id"].
								"\">{$row["fam_head"]}</a></td><td>" . $row['fam_no_of_members'] . "</td><td>" . $row['fam_no_of_children'] . "</td><td>" . $row['fam_last_migrated_from'] . "</td><td>" . $row['fam_traditional_occupation'] . "</td><td>" . $row['fam_daily_income'] . "</td><td>" . $row['fam_ration_card_status'] . "</td><td>" . $row['fam_ration_card_category'] . "</td><td>" . $row['fam_electricity_status'] . "</td><td>" . $row['fam_no_of_handicapped'] . "</td><td>" . $row['fam_janani_support_status'] . "</td><td>" . $row['fam_loan_application_status'] . "</td><td>" . $row['fam_water_connection'] . "</td><td>" . $row['fam_vraddh_pen_scheme'] . "</td><td>" . $row['Fam_plot_card_Status'] . "</td><td>" . $row['Fam_no_of_children_school'] . "</td><td>" . $row['Fam_settlement_date'] . "</td><td>" . $row['Fam_housing_support'] . "</td><td>" . $row['Fam_widow_pension_scheme'] . "</td></tr>";
							}
							//Table closing tag
							echo "</tbody></table>"; 			
						}
						//Display member information of a particular family in tabular form
						else if(!($fam==0)) {
							echo "<b><font size='5'>" . "Members Information" . "</font>" . "<br />" . "<br />";
							$result=mysql_query("Select * from member_info natural join family_info
							where fam_id=".$fam);
							echo "<table class=\"table table-bordered table-striped\">
							<thead><tr><th>Family Head</th><th>Member Name</th><th>Birthyear</th><th>Occupation</th><th>Relation with Head</th><th>Gender</th><th>Job Card Status</th><th>Voter Card Status</th><th>Aadhar Card</th></tr></thead>";
							echo "<tbody>";
							while($row = mysql_fetch_array($result))
							{
								//Display the results in different cells
								echo "<tr><td>" . $row['fam_head'] . "</td><td>" . $row['mem_name'] . "</td><td>" . $row['mem_birthyear']. "</td><td>" . $row['mem_occupation'] . "</td><td>" . $row['mem_relationwithead'] . "</td><td>" . $row['mem_gender'] . "</td><td>" . $row['mem_jobcard_status'] . "</td><td>" . $row['mem_voterstatus'] . "</td><td>" . $row['mem_aadhar_status'] . "</td></tr>";
							}
							//Table closing tag
							echo "</tbody></table>"; 
						}
						//Display item distribution information of a particular item in tabular form
						else if(!($item_id==0)) {
							echo "<b><font size='5'>" . "Item Distribution" . "</font>" . "<br />" . "<br />";
							$result=mysql_query("Select * from item_distribution where item_id=".$item_id);
							echo "<table class=\"table table-bordered table-striped\">
							<thead><tr><th>Item Name</th><th>Family Head</th><th>Number of Items</th><th>Settlement Name</th>
							<th>Coordinator Name</th></tr></thead>";
							echo "<tbody>";
							while($row = mysql_fetch_array($result))
							{
								$query="Select * from item_info where item_id=".$row['item_id'];
								$result1 = mysql_query($query, $connection);
								$row1=mysql_fetch_array($result1);
								$item_name=$row1['item_name'];
								$query="Select * from family_info where fam_id=".$row['fam_id'];
								$result2 = mysql_query($query, $connection);
								$row2=mysql_fetch_array($result2);
								$fam_name=$row2['fam_head'];
								$query="Select * from coordinator_info where coord_id=".$row['coord_id'];
								$result3 = mysql_query($query, $connection);
								$row3=mysql_fetch_array($result3);
								$coord_name=$row3['coord_name'];
								$query="Select * from settlement_info where set_id=".$row['set_id'];
								$result4 = mysql_query($query, $connection);
								$row4=mysql_fetch_array($result4);
								$set_name=$row4['set_name'];
								echo "<tr><td>$item_name</td><td>".$fam_name."</td><td>".$row['no_of_items']."</td><td>".$set_name.
								"<td>".$coord_name."</td></tr>";
							}
							//Table closing tag
							echo "</tbody></table>"; 
						}						
			?>
         
		</div><!--/span9-->
		  
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
