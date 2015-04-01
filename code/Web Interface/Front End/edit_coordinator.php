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

		  <!-- Display existing coordinator's list-->

		  <h2>Settings</h2>
			<hr>
			<h3><a name="changepass">Edit existing Coordinator's Information</a></h3>
			<br /><br /><br />
			    <form class="form-horizontal" action="edit_coordinator2.php" method="post">
				<fieldset>				
				Select the Coordinator whose information you want to edit:
				<br /><br />				
				<div class="control-group">
				<div class="controls">
				<?php
				$result=get_all_coordinators();
				$i=0;
				while($row=mysql_fetch_array($result)){
		      		echo "<label class=\"radio\">";
					if ($i==0){
			      	echo "<input type=\"radio\" checked name=\"coord\" id=".$row['coord_id']." value=".$row['coord_id'].">";
					}
					else{
					echo "<input type=\"radio\" name=\"coord\" id=".$row['coord_id']." value=".$row['coord_id'].">";
					}
				echo $row['coord_name'];
				echo "</label>";
				++$i;
				}
				?>
				</div>
				</div>
          
				<div class="form-actions">
				<button type="submit" class="btn btn-primary">Select</button>
				<button class="btn" name="cancel">Cancel</button>
				<?php
				if (isset($_POST['cancel'])){
				redirect_to("settings.php");
				}
				?>
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
