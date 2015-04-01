<?php require_once("../includes/connection.php") ?>
<?php require_once("../includes/functions.php") ?>

<?php 
include '../ofc/php-ofc-library/open-flash-chart.php';

    $title = new title( "Status of children going to school<br>" );
    $title->set_style( "{font-size: 20px; font-family: Times New Roman; font-weight: bold; color: #C35617; text-align: center;}" );
    $query="Select com_name, (SUM(fam_no_of_children)-SUM(fam_no_of_children_school)) as cnt1, SUM(fam_no_of_children_school) as cnt2
    from family_info natural join community_info GROUP BY com_name";
    $result=run_query_2($query);
    $data1 = array();
    $data2 = array();
    $labels = array();
    $bar1 = new bar_glass();
    $bar1->colour( '#BF3B69');
    $bar1->key('Not going to school', 12);
	$bar1->set_tooltip( "Not going to school<br> #val#" );
    
    $bar2 = new bar_glass();
    $bar2->colour( '#5E0722' );
    $bar2->key('School Going', 12);
    while($row = mysql_fetch_array($result))
          {
            $labels[]=$row['com_name'];
            $data1[] = intval($row['cnt1']);
            $data2[] = intval($row['cnt2']);
            
          }
    
    $bar1->set_values( $data1 );        
    $bar2->set_values( $data2 );
	$bar2->set_tooltip( "Going to school<br> #val#" );
    
    $x = new x_axis();
    $y = new y_axis();

    $y->set_range( 0, 13, 2);
    $x->set_labels_from_array($labels);  

    $chart = new open_flash_chart();
    $chart->set_title( $title );
    $chart->add_element( $bar1 );
    $chart->add_element( $bar2 );
      $chart->set_x_axis( $x );
      $chart->set_y_axis( $y );
      $chart->set_bg_colour( '#FFFFFF' );
    echo $chart->toString();
?>