<?php require_once("../includes/connection.php") ?>
<?php require_once("../includes/functions.php") ?>

<?php 
include '../ofc/php-ofc-library/open-flash-chart.php';

    $title = new title( "Ration Card Status<br>" );
    $title->set_style( "{font-size: 20px; font-family: Times New Roman; font-weight: bold; color: #C35617; text-align: center;}" );
    $query="Select com_name, Count(*) as cnt1 from family_info natural join community_info where fam_ration_card_status=\"ISSUED\"
    GROUP BY com_name";
    $result=run_query_2($query);
    $data1 = array();
    $labels = array();
    $bar1 = new bar_glass();
    $bar1->colour( '#BF3B69');
    $bar1->key('Issued', 12);
    $bar1->set_tooltip( "Issued<br> #val#" );
    
    while($row = mysql_fetch_array($result))
          {
            $labels[]=$row['com_name'];
            $data1[] = intval($row['cnt1']);
          }
    $bar1->set_values( $data1 );
    
    $query="Select com_name, Count(*) as cnt1 from family_info natural join community_info where fam_ration_card_status=\"APPLIED_NOT_ISSUED\"
    GROUP BY com_name";
    $result=run_query_2($query);
    $data2 = array();
    $bar2 = new bar_glass();
    $bar2->colour( '#5E0722');
    $bar2->key('Applied but not issued', 12);
    $bar2->set_tooltip( "Applied, not issued<br> #val#" );
    
    while($row = mysql_fetch_array($result))
          {
            $data2[] = intval($row['cnt1']);
          }
    $bar2->set_values( $data2 );
    
    $query="Select com_name, Count(*) as cnt1 from family_info natural join community_info where fam_ration_card_status=\"NOT_APPLIED\"
    GROUP BY com_name";
    $result=run_query_2($query);
    $data3 = array();
    $bar3 = new bar_glass();
    $bar3->colour( '#432BAF');
    $bar3->key('Not Applied', 12);
     $bar3->set_tooltip( "Not Applied<br> #val#" );
	 
    while($row = mysql_fetch_array($result))
          {
             $data3[] = intval($row['cnt1']);
          }
    
    $bar3->set_values( $data3 );        
    $x = new x_axis();
    $y = new y_axis();

    $y->set_range( 0, 3, 1);
    $x->set_labels_from_array($labels);  

    $chart = new open_flash_chart();
    $chart->set_title( $title );
    $chart->add_element( $bar1 );
    $chart->add_element( $bar2 );
    $chart->add_element( $bar3 );
      $chart->set_x_axis( $x );
      $chart->set_y_axis( $y );
      $chart->set_bg_colour( '#FFFFFF' );
    echo $chart->toString();
?>