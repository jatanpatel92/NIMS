<?php require_once("../includes/connection.php") ?>
<?php require_once("../includes/functions.php") ?>

<?php 
include '../ofc/php-ofc-library/open-flash-chart.php';

    $title = new title( "Average Daily Income<br>" );
    $title->set_style( "{font-size: 20px; font-family: Times New Roman; font-weight: bold; color: #C35617; text-align: center;}" );
    $query="Select com_name,  AVG(fam_daily_income) from family_info natural join community_info GROUP BY com_name";
    $result=run_query_2($query);
    $data1 = array();
    $labels = array();
    $bar1 = new bar_glass();
    $bar1->colour( '#BF3B69');
    //$bar1->key('Not going to school', 10);
    
    while($row = mysql_fetch_array($result))
          {
            $labels[]=$row['com_name'];
            $data1[] = intval($row['AVG(fam_daily_income)']);
          }
    
    $bar1->set_values( $data1 );        
    $x = new x_axis();
    $y = new y_axis();

    $y->set_range( 0, 850, 50);
    $x->set_labels_from_array($labels);  

    $chart = new open_flash_chart();
    $chart->set_title( $title );
    $chart->add_element( $bar1 );
      $chart->set_x_axis( $x );
      $chart->set_y_axis( $y );
      $chart->set_bg_colour( '#FFFFFF' );
    echo $chart->toString();
?>