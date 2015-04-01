<?php require_once("../includes/connection.php") ?>
<?php require_once("../includes/functions.php") ?>

<?php 
include '../ofc/php-ofc-library/open-flash-chart.php';

    $title = new title( "Traditional vs. Current Occupation<br>" );
    $title->set_style( "{font-size: 20px; font-family: Times New Roman; font-weight: bold; color: #C35617; text-align: center;}" );
    $query="Select com_name, Count(*) as count1 from family_info natural join member_info natural join community_info where
    (mem_occupation=fam_traditional_occupation) AND (mem_birthyear<1993) GROUP BY com_id";
    $result=run_query_2($query);
    $data1 = array();
    $data2 = array();
    $labels = array();
    $bar1 = new bar_glass();
    $bar1->colour( '#BF3B69');
    $bar1->key('Traditional', 12);
    $bar1->set_tooltip( "Traditional<br> #val#" );
    $bar2 = new bar_glass();
    $bar2->colour( '#5E0722' );
    $bar2->key('Current', 12);
    while($row = mysql_fetch_array($result))
    {
      $labels[]=$row['com_name'];
      $data1[] = intval($row['count1']);
    }
    
    $query="Select com_name, Count(*) as count1 from family_info natural join member_info natural join community_info where
    ((mem_occupation!=fam_traditional_occupation) OR (mem_occupation is NULL)) AND (mem_birthyear<1993) GROUP BY com_name";
    $result=run_query_2($query);
    while($row = mysql_fetch_array($result))
    {
      $data2[] = intval($row['count1']);
    }
    $bar2->set_tooltip( "Current<br> #val#" );
    $bar1->set_values( $data1 );        
    $bar2->set_values( $data2 );
    $x = new x_axis();
      $y = new y_axis();
// grid steps:
      $y->set_range( 0, 18, 2);

//
// Add the Y Axis object to the chart:
//

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