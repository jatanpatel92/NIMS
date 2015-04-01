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
        $query1="Select Count(*) as count1 from family_info natural join member_info where
				       (mem_occupation=fam_traditional_occupation) AND (mem_birthyear<1993) AND (com_id=".$id.")";
        $result1= run_query_2($query1,$fp);
        $row1=mysql_fetch_array($result1);
        $count1=intval($row1['count1']);
	       
        $query2="Select Count(*) as count2 from family_info natural join member_info where
        ((mem_occupation!=fam_traditional_occupation) OR (mem_occupation is NULL)) AND (mem_birthyear<1993) AND (com_id=".$id.")"  ;
        $result2= run_query_2($query2,$fp);
        $row2=mysql_fetch_array($result2);
        $count2=intval($row2['count2']);

        $data1 = array();     
        $data1[] = new pie_value($count1,"Traditional");
	$data1[] = new pie_value($count2,"Current");

         
           $pie = new pie();
          $pie->set_animate( true );
          $pie->add_animation( new pie_fade() );
          $pie->add_animation( new pie_bounce(4) );
          $pie->set_label_colour( '#432BAF' );
          $pie->set_alpha( 0.75 );
	  $pie->radius(130);
          //
          // This is where we turn of the labels,
          // but we use them inside the tooltip:
          //
          $pie->set_tooltip( '#label#<br>#val# (#percent#)' );
          //$pie->set_no_labels();
          
         $pie->set_colours(
              array(
                  '#77CC6D',    // income (green)
                  '#FF5973',    // spend (pink)
                  '#6D86CC'    // profit (blue)
              ) );
          
         $pie->set_values( $data1 );
          $title = new title( 'Traditional vs Current Occupation' );
          $title->set_style( "{font-size: 20px; font-family: Times New Roman; font-weight: bold; color: #C35617; text-align: center;}" );
          $chart = new open_flash_chart();
          $chart->set_title( $title );
          $chart->add_element( $pie );
           $chart->set_bg_colour( '#FFFFFF' );
          echo $chart->toPrettyString();
?>
