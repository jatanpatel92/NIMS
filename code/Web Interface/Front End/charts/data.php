<?php require_once("../includes/connection.php") ?>
<?php require_once("../includes/functions.php") ?>
<?php if (isset($_GET['id'])){
      $id=$_GET['id'];
      }else{
      $id=0;
      }
?>
<?php 
include '../ofc/php-ofc-library/open-flash-chart.php';
       
       
      
        $fp= fopen("../xml/charts.txt", "r");
        $c=fread($fp,2);
        $query="Select mem_gender, COUNT(mem_gender) FROM member_info natural join family_info where com_id=" . $id . " GROUP BY mem_gender ";
        $result=run_query_2($query);
          $data = array();
          while($row = mysql_fetch_array($result))
          {
            $data[] = new pie_value(intval($row['COUNT(mem_gender)']),($row['mem_gender']));
          }
         
          $pie = new pie();
          $pie->set_animate( true );
          $pie->add_animation( new pie_fade() );
          $pie->add_animation( new pie_bounce(4) );
          $pie->set_label_colour( '#432BAF' );
          $pie->set_alpha( 0.75 );
          $pie->radius(130);
          // This is where we turn of the labels,
          // but we use them inside the tooltip:
          //
          $pie->set_tooltip( '#label#<br>#val# (#percent#)' );
          //$pie->set_no_labels();
          //
         $pie->set_colours(
              array(
                  '#77CC6D',    // income (green)
                  '#FF5973',    // spend (pink)
                  '#6D86CC'    // profit (blue)
              ) );
          
          $pie->set_values( $data );
          $title = new title( 'Sex Ratio' );
          $title->set_style( "{font-size: 20px; font-family: Times New Roman; font-weight: bold; color: #C35617; text-align: center;}" );
          $chart = new open_flash_chart();
          $chart->set_title( $title );
          $chart->add_element( $pie );
           $chart->set_bg_colour( '#FFFFFF' );
          echo $chart->toPrettyString();
?>
