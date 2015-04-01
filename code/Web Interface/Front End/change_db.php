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
    <?php require_once("includes/connection.php") ?>
	<?php require_once("includes/functions.php") ?>
	<?php require_once("includes/session.php") ?>
    <?php confirm_logged_in(); ?>
    <?php
		if (isset($_POST['cancel'])) {
			redirect_to("settings.php");
		}
			
		if (isset($_POST['submit'])) {
			$valid_form = true;
			$error="";
			if($_GET['id']==1){
				$tal=mysql_prep($_POST['taluka']);
				$query="Select * from taluka_info where taluka_name=\"".$tal."\"";
				$result = mysql_query($query, $connection);
				$row=mysql_fetch_array($result);
				$tal_id=$row['taluka_id'];
				if ($_POST['vil'] == "") {
						$error .= "<br />Enter name of the village.";
						$valid_form = false;
				}
				else if(!preg_match("#^[-A-Za-z' ]*$#", $_POST['vil'])) {
						$error .= "<br />Enter proper(use alphabets only) village name.";
						$valid_form = false;
				}
				else {
					$vil_name = mysql_prep($_POST['vil']);
				}
				//$name = mysql_prep($_POST['name']);	
				if($valid_form == true) {
					$query = "INSERT INTO village_info (
						vil_name, taluka_id
					) VALUES (
					'{$vil_name}', {$tal_id} )";
					$result = mysql_query($query, $connection);
					if (mysql_affected_rows() == 1) {
						// Success
						$message = "The village was successfully added.";
					} else {
						// Failed
						$message = "No information was updated.";
						$message .= "<br />". mysql_error();
					}
				}
			}
		
		if($_GET['id']==2){
			$dist=mysql_prep($_POST['district']);
			$query="Select * from district_info where dist_name=\"".$dist."\"";
			$result = mysql_query($query, $connection);
			$row=mysql_fetch_array($result);
			$dist_id=$row['dist_id'];
			if ($_POST['tal'] == "") {
				$error .= "<br />Enter name of the taluka.";
				$valid_form = false;
			}
			else if(!preg_match("#^[-A-Za-z' ]*$#", $_POST['tal'])) {
				$error .= "<br />Enter proper(use alphabets only) taluka name.";
				$valid_form = false;
			}
			else {
				$tal_name = mysql_prep($_POST['tal']);
			}
			if($valid_form == true) {
				$query = "INSERT INTO taluka_info (
					taluka_name, dist_id
				) VALUES (
					'{$tal_name}', {$dist_id} )";
					
				$result = mysql_query($query, $connection);
				if (mysql_affected_rows() == 1) {
					// Success
					$message = "The taluka was successfully added.";
				} else {
					// Failed
					$message = "No information was updated.";
					$message .= "<br />". mysql_error();
				}
			}
		}
	}		
?>

    <meta charset="utf-8">
    <title>NIMS-Settings</title>
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
	       <li><a href="communities.php">Communities</a></li>
	       <li><a href="projects.php">Projects</a></li>
	       <li><a href="data_tables.php">Tabular Data</a></li>
	      <li class="active" ><a href="settings.php">Settings</a></li>
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
              <li class="nav-header">Settings</li>
              <li><a href="change_pass.php" >Change Password</a></li>
              <li><a href="add_coordinator.php">Add New Coordinator</a></li>
              <li><a href="edit_coordinator.php">Edit existing Coordinator's Information</a></li>
			  <li><a href="add_project.php">Add New Project</a></li>
			  <li ><a href="edit_project.php">Edit existing Project's Information</a></li>
              <li class="active"><a href="change_db.php">Add a new Village/Taluka</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span9">
          <div class="hero-unit">
            <h2>Settings</h2>
			<hr>
			<h3><a name="changepass">Edit existing Information</a></h3>
			<br />
			
			 <?php if (!empty($error)) {
				echo "Sorry! The information could not be added";
				echo  $error ;
				}
				if (!empty($message)) {
				echo "<p class=\"message\">" . $message . "</p>";
				}
			?>
				</br>
			<!--Form to add a new village -->
			<h3><a name="changepass">Add a new village</a></h3>
			<br />	
			    <form class="form-horizontal" action="change_db.php?id=1" method="post">
				<fieldset>
				<div class="control-group">
				<label class="control-label" for="focusedInput">Village Name</label>
				<div class="controls">
					<input class="input-xlarge focused"  name="vil" id="focusedInput" type="text"
					       value="" id="vil" />
				</div>
				</div>
				
				<div class="control-group">
				<label class="control-label" for="focusedInput">Taluka Name</label>
				<div class="controls">
					<select name="taluka">
					<?php 
					$query="Select * from taluka_info";
					$result = mysql_query($query, $connection);
					while($row=mysql_fetch_array($result)){
						echo "<option value=\"{$row['taluka_name']}\">{$row['taluka_name']}</option>";
					}
					?>
					</select>
				</div>
				</div>
         				
				<div class="form-actions">
				<button type="submit" class="btn btn-primary" name="submit">Add village</button>
				<button class="btn" name="cancel" >Cancel</button>
				</div>
        
				</fieldset>
			</form>
			
			<!--Form to add a new village -->
			<h3><a name="changepass">Add a new taluka</a></h3>
			<br />	
			    <form class="form-horizontal" action="change_db.php?id=2" method="post">
				<fieldset>
				<div class="control-group">
				<label class="control-label" for="focusedInput">Taluka Name</label>
				<div class="controls">
					<input class="input-xlarge focused"  name="tal" id="focusedInput" type="text"
					       value="" id="tal" />
				</div>
				</div>
				
				<div class="control-group">
				<label class="control-label" for="focusedInput">District Name</label>
				<div class="controls">
					<select name="district">
					<?php 
					$query="Select * from district_info";
					$result = mysql_query($query, $connection);
					while($row=mysql_fetch_array($result)){
						echo "<option value=\"{$row['dist_name']}\">{$row['dist_name']}</option>";
					}
					?>
					</select>
				</div>
				</div>
         				
				<div class="form-actions">
				<button type="submit" class="btn btn-primary" name="submit">Add taluka</button>
				<button class="btn" name="cancel" >Cancel</button>
				</div>
        
				</fieldset>
			</form>
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