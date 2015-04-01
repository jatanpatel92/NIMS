<!--
Copyright 2012 “Team 16, Btech 2009 batch, DA-IICT”
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and  limitations under the License.
-->
<?php require_once("connection.php");

$set_name=$_POST['get_families_info'];
$query="Select * from settlement_info where set_name='$set_name'";
$result = mysql_query($query, $connection);
$text="";
$no_of_row=mysql_num_rows($result);
if (($no_of_row>=1)){
        
    $row=mysql_fetch_array($result);
    $set_id=$row['set_id'];
    
    //$fp= fopen("data.xml", "w");
    $text ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" . "\n";
    $text .="<nims>"."\n";
    $text .="<settlement>".$set_name."</settlement>". "\n";
    
    $query = "SELECT * FROM family_info where set_id=".$set_id;
    $result = mysql_query($query, $connection);
    
    while($row=mysql_fetch_array($result)){
        $fam_id=$row['fam_id'];
        $text .="<family>"."\n";
        $text .= "\t<headname>".$row['fam_head']."</headname>"."\n";
        $text .= "\t<numofmembers>".$row['fam_no_of_members']."</numofmembers>"."\n";
        $text .= "\t<numofchildren>".$row['fam_no_of_children']."</numofchildren>"."\n";
        $text .= "\t<lastmigrated>".$row['fam_last_migrated_from']."</lastmigrated>"."\n";
        $text .= "\t<tradocc>".$row['fam_traditional_occupation']."</tradocc>"."\n";
        $text .= "\t<income>".$row['fam_daily_income']."</income>"."\n";
        $text .= "\t<commid>".$row['com_id']."</commid>"."\n";
        $text .= "\t<rationstatus>".$row['fam_ration_card_status']."</rationstatus>"."\n";
        $text .= "\t<rationcat>".$row['fam_ration_card_category']."</rationcat>"."\n";
        $text .= "\t<electricity>".$row['fam_electricity_status']."</electricity>"."\n";
        $text .= "\t<numofhandicapped>".$row['fam_no_of_handicapped']."</numofhandicapped>"."\n";
        $text .= "\t<jananistatus>".$row['fam_janani_support_status']."</jananistatus>"."\n";
        $text .= "\t<waterconn>".$row['fam_water_connection']."</waterconn>"."\n";
        $text .= "\t<vraddhsch>".$row['fam_vraddh_pen_scheme']."</vraddhsch>"."\n";
        $text .= "\t<plotcard>".$row['Fam_plot_card_Status']."</plotcard>"."\n";
        $text .= "\t<numofchildrenschool>".$row['Fam_no_of_children_school']."</numofchildrenschool>"."\n";
        $text .= "\t<housingsupport>".$row['Fam_housing_support']."</housingsupport>"."\n";
        $text .= "\t<widowscheme>".$row['Fam_widow_pension_scheme']."</widowscheme>"."\n";
    
        $query2 = "SELECT * FROM member_info where fam_id=".$row['fam_id'];
        $result2 = mysql_query($query2, $connection);
        while($row2=mysql_fetch_array($result2)){
            $text .="\t<member>"."\n";
            $text .="\t\t<memname>".$row2['mem_name']."</memname>"."\n";
            $text .="\t\t<birthyear>".$row2['mem_birthyear']."</birthyear>"."\n";
            $text .="\t\t<occupation>".$row2['mem_occupation']."</occupation>"."\n";
            $text .="\t\t<relationwithhead>".$row2['mem_relationwithead']."</relationwithhead>"."\n";
            $text .="\t\t<gender>".$row2['mem_gender']."</gender>"."\n";
            $text .="\t\t<jobcardstatus>".$row2['mem_jobcard_status']."</jobcardstatus>"."\n";
            $text .="\t\t<voterstatus>".$row2['mem_voterstatus']."</voterstatus>"."\n";
            $text .="\t\t<aadharstatus>".$row2['mem_aadhar_status']."</aadharstatus>"."\n";
            $text .="\t</member>"."\n";
        }
        $text .="</family>"."\n";
    }
    
    $text .="</nims>"."\n";
    //fwrite($fp, $text);
}
echo $text;

?>

