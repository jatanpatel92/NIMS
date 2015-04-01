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
        
        $query="Select Count(*) as cnt1 from family_info where fam_ration_card_category=\"APL\" AND com_id=".$id;
        $result=run_query_2($query);
        $data = array();
        $row = mysql_fetch_array($result);
        
        $query="Select Count(*) as cnt2 from family_info where fam_ration_card_category=\"BPL\" AND com_id=".$id;
        $result2=run_query_2($query);
        $row2 = mysql_fetch_array($result2);
        
        $query="Select Count(*) as cnt3 from family_info where fam_ration_card_category=\"ANTYODAY\" AND com_id=".$id;
        $result3=run_query_2($query);
        $row3 = mysql_fetch_array($result3);
        $data[] = new pie_value(intval($row['cnt1']),('Issued'));
        $data[] = new pie_value(intval($row2['cnt2']),('Applied but not issued'));
        $data[] = new pie_value(intval($row3['cnt3']),('Not Applied'));          
         
          $pie = new pie();
          $pie->set_animate( true );
          $pie->add_animation( new pie_fade() );
          $pie->add_animation( new pie_bounce(4) );
          $pie->set_label_colour( '#432BAF' );
          $pie->set_alpha( 0.75 );
          //$pie->set_no_labels();
          $pie->radius(130);
          //
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
          $title = new title( 'Ration Card Category' );
          $title->set_style( "{font-size: 20px; font-family: Times New Roman; font-weight: bold; color: #C35617; text-align: center;}" );
          $chart = new open_flash_chart();
          $chart->set_title( $title );
          $chart->add_element( $pie );
           $chart->set_bg_colour( '#FFFFFF' );
          echo $chart->toPrettyString();
?>
