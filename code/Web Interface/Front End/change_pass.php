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
				$sel_ngo=get_one_ngo($_POST['username']);
				
				$valid_form = true;
				$error = "";
				//$error .=$_POST['username'];
				//$error .=$sel_ngo['NGO_username'];
				
				if ($_POST['username'] == "") {
					$error .= "</br>Enter Username.";
					$valid_form = false;
				}
				else {
					if ($sel_ngo == NULL) {
						$error .= "</br>No such username exist.";
						$valid_form = false;
					}
					else {
						$oldpass = ($sel_ngo['NGO_password']);
						if(!($oldpass == $_POST['old'])) {
							$error .= "</br>Enter correct old password.";
							$valid_form = false;
						}
						else {
							if(!($_POST['new'] == $_POST['confirm'])) {
								$error .= "</br>New password doesn't match with confirm password.";
								$valid_form = false;
							}
							else if($_POST['new'] == NULL) {
							$error .= "</br>Enter new password.";
							$valid_form = false;
							}
							else {
							$new_password=mysql_prep($_POST['new']);;
							}
						}
						
					}
				}
				if($valid_form == true) {
				$query = "UPDATE ngo SET NGO_password = ". "\"" . $new_password . "\" " . "where NGO_username = ". "\"" . $sel_ngo['NGO_username'] . "\" ";
				$result = mysql_query($query, $connection);
				if (mysql_affected_rows() == 1) {
					// Success
					$message = "The Password was successfully changed.";
				} else {
					// Failed
					$message = "No information was updated.";
					$message .= "<br />". mysql_error();
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
              <li class="active"><a href="change_pass.php" >Change Password</a></li>
              <li><a href="add_coordinator.php">Add New Coordinator</a></li>
              <li><a href="edit_coordinator.php">Edit existing Coordinator's Information</a></li>
			  <li><a href="add_project.php">Add New Project</a></li>
			  <li><a href="edit_project.php">Edit existing Project's Information</a></li>
              <li><a href="change_db.php">Add a new Village/Taluka</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span9">
          <div class="hero-unit">
            <h2>Settings</h2>
			<hr>
			<!-- Form to change the password -->
			<h3><a name="changepass">Change Password</a></h3>
			<br />
			
			 <?php if (!empty($error)) {
				echo "<p class=\"message\">" ."Sorry! The password can't be changed.".$error."</p>" ;
				}
				if (!empty($message)) {
				echo "<p class=\"message\">" . $message . "</p>";
				}
				?>
				</br>
				
            <form class="form-horizontal" action="change_pass.php" method="post">
				<fieldset>
				<div class="control-group">
				<label class="control-label" for="focusedInput">Enter Username</label>
				<div class="controls">
					<input class="input-xlarge focused" name="username" id="focusedInput" type="text">
				</div>
				</div>
				
				<div class="control-group">
				<label class="control-label" for="focusedInput">Enter Old Password</label>
				<div class="controls">
					<input class="input-xlarge focused" name= "old" id="focusedInput" type="password">
				</div>
				</div>
          
				<div class="control-group">
				<label class="control-label" for="focusedInput">Enter New Password</label>
				<div class="controls">
					<input class="input-xlarge focused" name="new" id="focusedInput" type="password">
				</div>
				</div>
				
				<div class="control-groups">
				<label class="control-label" for="focusedInput">Confirm New Password</label>
				<div class="controls">
					<input class="input-xlarge focused" name="confirm" id="focusedInput" type="password">
				</div>
				</div>
          
				<div class="form-actions">
				<button type="submit" class="btn btn-primary" name="submit">Save changes</button>
				<button class="btn" name="cancel">Cancel</button>
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
