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
    <?php if (isset($_POST['cancel'])){
	  redirect_to("settings.php");
	}
    ?>
	<?php
		/* Below code is for validation of inputs of add project form and if it is valid then add project in database*/
		$valid_form = true;
		$error = "";
		if (isset($_POST['submit'])){
		if ($_POST['name'] == ""){
			$error .= "</br>Enter name of the Project.";
			$valid_form = false;
		}elseif(!preg_match("#^[-A-Za-z' ]*$#",$_POST['name'])){
			$error .= "</br>Enter proper(use alphabets only) Project name.";
			$valid_form = false;
		}else{
			$name = mysql_prep($_POST['name']);
		}
		
		if ($_POST['categ'] == ""){
			$error .= "</br>Enter Project Category";
			$valid_form = false;
		}elseif(!preg_match("#^[-A-Za-z' ]*$#",$_POST['categ'])){
			$error .= "</br>Only use alphabets in Project Category.";
			$valid_form = false;
		}else{
			$category = mysql_prep($_POST['categ']);
		}
		
		if (!preg_match('/^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/',$_POST['start'])){
			$error .= ("<br />"."Enter proper project start date in YYYY-MM-DD format.");
			$valid_form = false;
		}else{
			$arr = explode("-", $_POST['start']);
			$a=$arr[0] . $arr[1] . $arr[2];
			$start = mysql_prep($a);
		}
				
		if ($_POST['end'] == ""){	
			$d=date("Y-m-d");
			$arr = explode("-", $d);
			$a=$arr[0] . $arr[1] . $arr[2];
			$end = mysql_prep($a);
		} else {
		if (!preg_match('/^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/',$_POST['end'])){
			$error .= ("<br />"."Enter proper project end date in YYYY-MM-DD format.");
			$valid_form = false;
		}else{
			$arr = explode("-", $_POST['end']);
			$a=$arr[0] . $arr[1] . $arr[2];
			$end = mysql_prep($a);
		}
		}
		
		if ($_POST['milestones'] == ""){
			$m= "No milestones till now";
			$milestones = mysql_prep($m);
		}else{
		$milestones = mysql_prep($_POST['milestones']);
		}
		
		if ($_POST['donors'] == ""){
			$error .= "</br>Enter name of project main donors.";
			$valid_form = false;
		}else{
		$donors = mysql_prep($_POST['donors']);
		}
		
		if ($_POST['settl'] == ""){
			$error .= "</br>Enter settlement name.";
			$valid_form = false;
		}else{
			$qury=get_setid($_POST['settl']);
			if($qury == NULL){
				$error .= "</br>Enter existing settlement name.";
				$valid_form = false;
			}else{
				$setid=$qury['set_id'];
				$settlements = mysql_prep($setid);
			}
		}
	
		if($valid_form == true){
		$query = "INSERT INTO project_info (
				PROJ_NAME, PROJ_ST_DATE, PROJ_END_DATE, PROJ_MILESTONES, PROJ_CATEGORY, PROJ_DONORS,PROJ_SETTLEMENT_ID
				) VALUES (
				'{$name}', {$start}, {$end}, '{$milestones}', '{$category}', '{$donors}','{$settlements}'
				)";
		$result = mysql_query($query, $connection);
		if ($result) {
		$message ="The Project was successfully added.";
		} 
		}
	}	
?>

<?php mysql_close($connection); ?>

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
              <li><a href="edit_coordinator.php">Edit existing Coordinator's Information</a></li>
			  <li class="active"><a href="add_project.php">Add New Project</a></li>
			  <li><a href="edit_project.php">Edit existing Project's Information</a></li>
              <li><a href="change_db.php">Add a new Village/Taluka</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span9">
          <div class="hero-unit">
            <h2>Settings</h2>
			<hr>
			<h3><a name="changepass">Add New Project</a></h3>
			<br />
			<?php if(!(empty($error))){
						echo "<p class=\"message\">" ."Sorry! The Project couldn't be added".$error."</p>" ;
					}
					elseif (!(empty($message))) 
						echo "<p class=\"message\">" . $message . "</p>";
					echo "</br></br>";
			?>
			<form class="form-horizontal" action="add_project.php" method="post">
				<fieldset>
				<div class="control-group">
				<label class="control-label" for="focusedInput">Project's Name</label>
				<div class="controls">
					<input class="input-xlarge focused" name="name" id="focusedInput" type="text" id="name">
				</div>
				</div>
				
				<div class="control-group">
				<label class="control-label" for="focusedInput">Project Category</label>
				<div class="controls">
					<input class="input-xlarge focused" name="categ" id="focusedInput" type="text">
				</div>
				</div>
          
				<div class="control-group">
				<label class="control-label" for="focusedInput">Project Start Date (YYYY-MM-DD)
				</label>
				<div class="controls">
					<input class="input-xlarge focused" name="start" id="focusedInput" type="text">
				</div>
				</div>
				
				<div class="control-groups">
				<label class="control-label" for="focusedInput">Project End Date (YYYY-MM-DD)</label>
				<div class="controls">
					<input class="input-xlarge focused" name="end" id="focusedInput" type="text">
				</div>
				</div>
				<br />
				
				<div class="control-groups">
				<label class="control-label" for="focusedInput">Project Milestones</label>
				<div class="controls">
					<input class="input-xlarge focused" name="milestones" id="focusedInput" type="text">
				</div>
				</div>
				</br>
          
		  		<div class="control-group">
				<label class="control-label" for="focusedInput">Project Main Donors
				</label>
				<div class="controls">
					<input class="input-xlarge focused" name="donors" id="focusedInput" type="text">
				</div>
				</div>
				
				<div class="control-group">
				<label class="control-label" for="focusedInput">Settlement Name
				</label>
				<div class="controls">
					<input class="input-xlarge focused" name="settl" id="focusedInput" type="text">
				</div>
				</div>
				
				<div class="form-actions">
				<button type="submit" class="btn btn-primary" name="submit">Add Project</button>
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