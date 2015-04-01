<!--Copyright 2012 "Team 16, BTech 2009 Batch, DA-IICT"

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.-->
<!DOCTYPE html>
<html lang="en">
  <head>
    <?php require_once("includes/connection.php") ?>
	<?php require_once("includes/functions.php") ?>
	<?php require_once("includes/session.php") ?>
    <?php confirm_logged_in(); ?>
    <?php
		/*Below code is for validation of inputs in edit coordinator form and if it is valid then edit coordinator's information in database*/
		if(isset($_POST['coord'])){  
			$sel_coord=$_POST['coord'];
        }else{
            $sel_coord=0;
        }
	
		if(isset($_GET['addcoord']))
        {   $sel_coord=$_GET['addcoord'];
        }           	
		$sel_coordinator=get_one_coordinator($sel_coord);
		$id = ($sel_coordinator['coord_id']);
				
			if (isset($_POST['submit'])) {
				$valid_form = true;
				$error="";
			if ($_POST['name'] == ""){
					$error .= "</br>Enter name of the coordinator.";
					$valid_form = false;
			}elseif(!preg_match("#^[-A-Za-z' ]*$#", $_POST['name'])){
					$error .= "</br>Enter proper(use alphabets only) coordinator name.";
					$valid_form = false;
			}else{
					$name = mysql_prep($_POST['name']);
			}
			//$name = mysql_prep($_POST['name']);
			if (strlen($_POST['contact']) != 10 ){
				$error .= "</br>Contact(mobile) number must be of 10 digits.";
				$valid_form = false;
			}elseif(!preg_match('/^[0-9]{0,}$/'/*'/^\d+$/'*/, $_POST['contact'])){
					$error .= "</br>Enter proper Contact(mobile) number.";
					$valid_form = false;
			}else{
				$contact = mysql_prep($_POST['contact']);
				}
				//$contact = mysql_prep($_POST['contact']);
			if (!preg_match('/^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/',$_POST['join'])){
				$error .= ("</br>Enter proper date in YYYY-MM-DD format.");
				$valid_form = false;
			}else{
				$arr = explode("-", $_POST['join']);
				$a=$arr[0] . $arr[1] . $arr[2];
				$join = mysql_prep($a);
			}
			//$join = mysql_prep($_POST['join']);
			if ($_POST['username'] == ""){		
				$error .= "</br>Enter username.";
				$valid_form = false;
			}else{
				$username = mysql_prep($_POST['username']);
			}
			//$username = mysql_prep($_POST['username']);
			if ($_POST['password'] == ""){
				$error .= "</br>Enter a password.";
				$valid_form = false;
			}elseif (strlen($_POST['password']) < 4){
				$error .= "</br>Password must contain at least 4 characters.";
				$valid_form = false;
			}else{
				$password = mysql_prep($_POST['password']);
				}
				//$password = mysql_prep($_POST['password']);
				
			if($valid_form == true)
			{
				$query = "UPDATE coordinator_info SET coord_name = ". "\"" . $name . "\", " . "coord_contactno = {$contact}," .
				"coord_joining_date = ". "\"" . $join . "\" ," .
				"coord_username = ". "\"" . $username . "\" ," .
				"coord_password = ". "\"" . $password . "\" "	
				. " where coord_id = {$id} ";
				
				$result = mysql_query($query, $connection);
				if (mysql_affected_rows() == 1) {
					// Success
					$message = "The information was successfully updated.";
				} else {
					// Failed
					$message = "No information was updated.";
					$message .= "<br />". mysql_error();
				}
			}	
				// Errors occurred
				//$message = "There were " . count($errors) . " errors in the form.";
			}	$sel_coordinator=get_one_coordinator($sel_coord);
				
			if (isset($_POST['cancel'])){
				  redirect_to("settings.php");
				}
				
			if (isset($_POST['delete'])) {
			    if ($coordinator = get_one_coordinator($sel_coord)) {      
				      $query = "DELETE FROM coordinator_info WHERE coord_id = {$sel_coord} LIMIT 1";
				      $result = mysql_query($query, $connection);
				      if (mysql_affected_rows() == 1) {
					      $message="The coordinator was successfully deleted.";
						  redirect_to("settings.php?msg=2");
				      } else {
					      // Deletion Failed
					      $message="Coordinator deletion failed.";
					      $message .= "<br />". mysql_error();
				      }
				} else {
				      $message="Coordinator didn't exist in database.";
				}
			}
			$sel_coordinator=get_one_coordinator($sel_coord);			
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
          <a class="brand">NIMS</a>
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
              <li class="active"><a href="edit_coordinator.php">Edit existing Coordinator's Information</a></li>
			  <li><a href="add_project.php">Add New Project</a></li>
			  <li><a href="edit_project.php">Edit existing Project's Information</a></li>
              <li><a href="change_db.php">Add a new Village/Taluka</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span9">
          <div class="hero-unit">
		  <!-- code for Form of edit existing coordinator's information -->
            <h2>Settings</h2>
			<hr>
			<h3><a name="changepass">Edit existing Coordinator's Information</a></h3>
			<br />
			 <?php if (!empty($error)) {
				echo "<p class=\"message\">" ."Sorry! The information could not be edited.".$error."</p>" ;
				}
				if (!empty($message)) {
				echo "<p class=\"message\">" . $message . "</p>";
				}
				?>
				</br>
				
			    <form class="form-horizontal" action="edit_coordinator2.php?addcoord=<?php echo urlencode($sel_coordinator['coord_id']); ?>" method="post">
				<fieldset>
				<div class="control-group">
				<label class="control-label" for="focusedInput">Coordinator's Name</label>
				<div class="controls">
					<input class="input-xlarge focused"  name="name" id="focusedInput" type="text"
					       value="<?php echo $sel_coordinator['coord_name']; ?>" id="name" />
				</div>
				</div>
				
				<div class="control-group">
				<label class="control-label" for="focusedInput">Coordinator's Contact No.</label>
				<div class="controls">
					<input class="input-xlarge focused"  name="contact" id="focusedInput" type="text"
					       value="<?php echo $sel_coordinator['coord_contactno']; ?>" id="contact" />
				</div>
				</div>
          
				<div class="control-group">
				<label class="control-label" for="focusedInput">Coordinator's Joining Date (YYYY-MM-DD)
				</label>
				<div class="controls">
					<input class="input-xlarge focused"  name="join" id="focusedInput" type="text"
					       value="<?php echo $sel_coordinator['coord_joining_date']; ?>" id="join" />
				</div>
				</div>
				
				<div class="control-groups">
				<label class="control-label" for="focusedInput">Coordinator's Username</label>
				<div class="controls">
					<input class="input-xlarge focused"  name="username" id="focusedInput" type="text"
					       value="<?php echo $sel_coordinator['coord_username']; ?>" id="username" />
				</div>
				</div>
				<br />
				
				<div class="control-groups">
				<label class="control-label" for="focusedInput">Coordinator's Password</label>
				<div class="controls">
					<input class="input-xlarge focused"  name="password" id="focusedInput" type="text"
					       value="<?php echo $sel_coordinator['coord_password']; ?>" id="password" />
				</div>
				</div>
          
				<div class="form-actions">
				<button type="submit" class="btn btn-primary" name="submit">Save Changes</button>
				<button type="submit" class="btn btn-primary" name="delete" onclick="return confirm('Are you sure?');">Delete Coordinator</button>
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
