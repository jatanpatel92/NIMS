<!DOCTYPE html>
<?php require_once("includes/functions.php") ?>
<?php require_once("includes/connection.php") ?>
<?php require_once("includes/session.php") ?>
    <?php confirm_logged_in(); ?>
<?php
            if(isset($_GET['coord'])){
                    $sel_coord=$_GET['coord'];
            } else { $sel_coord=0; }
            $sel_coordinator=get_one_coordinator($sel_coord);
?>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>NIMS-Coordinators</title>
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
	      <li class="active"><a href="coordinator.php">Coordinators</a></li>
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
              <li class="nav-header">Coordinator</li>
              <?php
        
	      $result=get_all_coordinators();
	      while($row=mysql_fetch_array($result)){
                   echo "<li";
                    if($row['coord_id']==$sel_coord){
                            echo " class=\"selected\"";
                    }
                    echo "><a href=\"coordinator.php?coord=" . urlencode($row['coord_id']).
                    "\">{$row['coord_name']}</a></li>";
	      }	
	      ?>
              <li><a href="#">Link</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span6">
          <div class="hero-unit">
            <h3>Coordinator's Travail</h3>
            <p>i.e. given his work targets and work done, how much area has he covered</p>
          </div><!--/hero-unit-->
		  <div class="row-fluid">
			<div class="hero-unit">
			
			<h3> Coordinator's Performance </h3><br />
			<p>
			<?php
                            if($sel_coordinator!=0)
                                echo "Name: " . $sel_coordinator['coord_name']."<br /><br />";
                        ?>
			</p>
			</div><!--/hero-unit-->
	      </div><!--/row-->
		</div><!--/span-->
		  <div class="span3 offset8">
          <div class="hero-unit">
            <h3>Coordinator Information</h3><br />
	    <p>
	    <?php
                    if($sel_coordinator!=0){ 
                        echo "Name: " . "<br />" . $sel_coordinator['coord_name']."<br /><br />"; 
                        echo "Contact No: " . "<br />" . $sel_coordinator['coord_contactno']."<br /><br />"; 
                        echo "Joining Date: " . "<br />" . $sel_coordinator['coord_joining_date']."<br /><br />";
                        echo "Username: " . "<br />" . $sel_coordinator['coord_username']; 
                     }
	    ?>
	    </p>
	    
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
