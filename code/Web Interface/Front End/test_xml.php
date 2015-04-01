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
<?php require_once("includes/connection.php") ?>
<?php require_once("includes/functions.php") ?>
<?php
$data='<?xml version="1.0"?>
        <nims>
		  <coordinator_name>bharat</coordinator_name>
                <village value="Rampur">
                        <settlement value="set1">
                                <family>
                                        <headname>anshul</headname> 
                                        <numofmembers>5</numofmembers>
                                        <numofchildren>2</numofchildren>
                                        <lastmigrated>sda</lastmigrated>
                                        <tradocc>sdf</tradocc>
                                        <income>13</income>
                                        <commid>2</commid>
                                        <rationstatus>NOT_APPLIED</rationstatus>
                                        <rationcat>APL</rationcat>
                                        <electricity>NOT_APPLIED</electricity>
                                        <numofhandicapped>2</numofhandicapped>
                                        <jananistatus>NOT_APPLIED</jananistatus>
                                        <waterconn>NOT_APPLIED</waterconn>
                                        <vraddhsch>NOT_APPLIED</vraddhsch>
                                        <plotcard>NOT_APPLIED</plotcard>
                                        <numofchildrenschool>1</numofchildrenschool>
                                        <housingsupport>NOT_APPLIED</housingsupport>
                                        <widowscheme>NOT_APPLIED</widowscheme>
                                        <member>
                                                <memname>sdfsf</memname> 
                                                <birthyear>6412</birthyear>
                                                <occupation>asd</occupation>
                                                <relationwithhead>sd</relationwithhead>
                                                <gender>M</gender>
                                                <jobcardstatus>NOT_APPLIED</jobcardstatus>
                                                <voterstatus>NOT_APPLIED</voterstatus>
                                                <aadharstatus>NOT_APPLIED</aadharstatus>
                                        </member>
                                </family>
								<place>
                                         <place_type>a</place_type>
                                         <name>b</name>
                                         <latitude>90.8923</latitude>
                                         <longitude>89.902</longitude>
                                </place>
                                <place>
                                         <place_type>ab</place_type>
                                         <name>bc</name>
                                         <latitude>91.8923</latitude>
                                         <longitude>90.902</longitude>
                                </place>
								<item_distribution>
                                          <item_name>AB</item_name>
										  <family>
                                               <headname>Magan</headname> 
                                                <no_items>12</no_items>
										</family>
										<family>
                                              <headname>Gopal</headname> 
                                                  <no_items>2</no_items>
                                          </family> 
                                </item_distribution> 
								<item_distribution>
                                          <item_name>CD</item_name>
										  <family>
                                               <headname>Babu</headname> 
                                                <no_items>12</no_items>
										</family>
										<family>
                                              <headname>Kanu</headname> 
                                                  <no_items>2</no_items>
                                          </family> 
                                </item_distribution>
								<item_distribution>
                                          <item_name>PQ</item_name>
										  
										  <family>
                                              <headname>Champa</headname> 
                                                  <no_items>2</no_items>
                                          </family> 
                                </item_distribution>																
                        </settlement>
                </village>
        </nims>';
        
$nims = new SimpleXMLElement($data);
$coord=$nims->coordinator_name;
$query = "Select * from coordinator_info where ((coord_name='{$nims->coordinator_name}'))";
$result = mysql_query($query, $connection);
$row=mysql_fetch_array($result);
$coord_id=$row['coord_id'];
echo $coord_id;

foreach($nims->children() as $vil) {	
        $query="Select vil_id from village_info where vil_name='{$vil['value']}'";
        $result = mysql_query($query, $connection);
       
        $row=mysql_fetch_array($result);
        $vil_id=$row['vil_id'];
       
        foreach($vil->settlement as $set){
                $query="Select * from settlement_info natural join village_info where vil_id=".$vil_id." and set_name='{$set['value']}'";
                $result = mysql_query($query, $connection);
                $no_of_row=mysql_num_rows($result);
                if (!($no_of_row>=1)){
                        $query = "INSERT INTO settlement_info (set_name, vil_id) VALUES ('{$set['value']}',$vil_id)";
                        $result = mysql_query($query, $connection);   
                }
                
                $query = "Select set_id from settlement_info where set_name='{$set['value']}'";
                $result = mysql_query($query, $connection);
                $row=mysql_fetch_array($result);
                $set_id=$row['set_id'];
                
                
                foreach($set->family as $fam){
                        $query="Select * from family_info natural join settlement_info where fam_head='{$fam->headname}'
                        and fam_no_of_members={$fam->numofmembers} and set_name='{$set['value']}'";
                        $result = mysql_query($query, $connection);
                        $no_of_row=mysql_num_rows($result);
                        if (($no_of_row>=1)){
                                $query="UPDATE family_info SET fam_no_of_children={$fam->numofchildren},
                                fam_last_migrated_from='{$fam->lastmigrated}', fam_traditional_occupation='{$fam->tradocc}',
                                fam_daily_income={$fam->income}, com_id={$fam->commid}, set_id=$set_id,
                                fam_ration_card_status='{$fam->rationstatus}', fam_ration_card_category='{$fam->rationcat}',
                                fam_electricity_status='{$fam->electricity}', fam_no_of_handicapped={$fam->numofhandicapped},
                                fam_janani_support_status='{$fam->jananistatus}', fam_water_connection='{$fam->waterconn}',
                                fam_vraddh_pen_scheme='{$fam->vraddhsch}', Fam_plot_card_Status='{$fam->plotcard}',
                                Fam_no_of_children_school={$fam->numofchildrenschool}, Fam_housing_support='{$fam->housingsupport}',
                                Fam_widow_pension_scheme='{$fam->widowscheme}' where fam_head='{$fam->headname}' and
                                fam_no_of_members={$fam->numofmembers}";
                        }
                        else{
                                $query = "INSERT INTO family_info (fam_head, fam_no_of_members, fam_no_of_children,
                                fam_last_migrated_from, fam_traditional_occupation, fam_daily_income, com_id, set_id, fam_ration_card_status,
                                fam_ration_card_category, fam_electricity_status, fam_no_of_handicapped, fam_janani_support_status,
                                fam_water_connection, fam_vraddh_pen_scheme,Fam_plot_card_Status,Fam_no_of_children_school,
                                Fam_housing_support,Fam_widow_pension_scheme) VALUES
                                 ('{$fam->headname}', {$fam->numofmembers}, {$fam->numofchildren}, '{$fam->lastmigrated}', '{$fam->tradocc}',{$fam->income},
                                 {$fam->commid}, $set_id, '{$fam->rationstatus}', '{$fam->rationcat}', '{$fam->electricity}', {$fam->numofhandicapped},
                                 '{$fam->jananistatus}', '{$fam->waterconn}', '{$fam->vraddhsch}', '{$fam->plotcard}', {$fam->numofchildrenschool},
                                 '{$fam->housingsupport}', '{$fam->widowscheme}')";
                        }
                        $result = mysql_query($query, $connection);
                        
                        $query = "Select * from family_info where ((fam_head='{$fam->headname}') and (fam_last_migrated_from='{$fam->lastmigrated}'))";
                        $result = mysql_query($query, $connection);
                        $row=mysql_fetch_array($result);
                        $fam_id=$row['fam_id'];
                       
                        $i=1;
                        foreach($fam->member as $mem) {
                                $query="Select * from member_info natural join family_info where fam_id=$fam_id";
                                $result = mysql_query($query, $connection);
                                $no_of_row=mysql_num_rows($result);
                                if (($no_of_row>=1)){
                                $query="UPDATE member_info SET mem_birthyear={$mem->birthyear}, mem_occupation='{$mem->occupation}',
                                mem_relationwithead='{$mem->relationwithhead}', mem_gender='{$mem->gender}',
                                mem_jobcard_status='{$mem->jobcardstatus}', mem_voterstatus='{$mem->voterstatus}',
                                mem_aadhar_status='{$mem->aadharstatus}' where mem_name='{$mem->memname}' and fam_ID=".$fam_id;
                                }
                                else{
                                $query = "INSERT INTO member_info (mem_ID, fam_ID, mem_name, mem_birthyear, mem_occupation,
                                mem_relationwithead, mem_gender, mem_jobcard_status, mem_voterstatus, mem_aadhar_status) VALUES
                                ( $i, $fam_id, '{$mem->memname}', {$mem->birthyear}, '{$mem->occupation}', '{$mem->relationwithhead}',
                                '{$mem->gender}', '{$mem->jobcardstatus}', '{$mem->voterstatus}', '{$mem->aadharstatus}')";
                                }
                                $result = mysql_query($query, $connection);
                                
                                ++$i;
                        }
                }
				foreach($set->place as $map){
					$query = "INSERT INTO socialmap(latitude, longitude, type, Name, village) VALUES
					({$map->latitude}, {$map->longitude}, '{$map->place_type}', '{$map->name}','{$vil['value']}')";
					$result = mysql_query($query, $connection);
				}
				foreach($set->item_distribution as $item){
					$query="Select * from item_info where item_name="."\"{$item->item_name}\"";
					$result = mysql_query($query, $connection);
					$no_of_rows=0;
					if($result){
						$no_of_row=mysql_num_rows($result);}
                    if ($no_of_row<1){
						$query="INSERT into item_info (item_name) VALUES ('{$item->item_name}')";
						$result = mysql_query($query, $connection);
					}
					$query="Select * from item_info where item_name="."\"{$item->item_name}\"";
					$result = mysql_query($query, $connection);
					$row=mysql_fetch_array($result);
					$item_id=$row['item_id'];
					
					foreach($item->family as $itemf){
						$query = "Select * from family_info where ((fam_head='{$itemf->headname}'))";
						$result = mysql_query($query, $connection);
						$row=mysql_fetch_array($result);
						$fam_id=$row['fam_id'];
						
						$query="INSERT into item_distribution (item_id, fam_id, no_of_items, set_id, coord_id ) 
						VALUES ($item_id, $fam_id, {$itemf->no_items}, $set_id, $coord_id)";
						$result = mysql_query($query, $connection);
					}
    			}	
        }      
}
 echo 'Successfully Updated';
?>