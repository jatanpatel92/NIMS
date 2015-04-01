<?php
    function confirm_query($result_set){
        if(!$result_set){
            die("Database query failed: ". mysql_error());
        }
    }
    
    function charts($query, $fp){
	global $connection;
	$text ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" . "\n";
	$text .="<JSChart>". "\n";
	$text .="<dataset type=\"pie\">". "\n";
	fwrite($fp, $text);
	$result= mysql_query($query, $connection);
	confirm_query($result);
	return($result);
    }
    
    function run_query($query) {
	global $connection;
	$result= mysql_query($query, $connection);
	confirm_query($result);
	$rows = mysql_numrows($result);
	if ($row=mysql_fetch_array($result)){
            return $row;
        } else {
            return NULL;
        }
    }
    
    function run_query_2($query) {
		global $connection;
		$result= mysql_query($query, $connection);
		confirm_query($result);
		return $result;
    }
    
    function get_all_communities(){
        global $connection;
        $query="SELECT * FROM community_info";
		$result= mysql_query($query, $connection);
		confirm_query($result);
        return $result;
    }
	    function get_ngo_info(){
        global $connection;
        $query="SELECT * FROM ngo";
		$result= mysql_query($query, $connection);
		confirm_query($result);
        return $result;
    }
	
	function get_one_ngo($username){
        global $connection;
        $query="SELECT * from ngo where NGO_username =" . "\"" . $username . "\" ";
        $result=mysql_query($query, $connection);
        confirm_query($result);
        if ($row=mysql_fetch_array($result)){
            return $row;
        } else {
            return NULL;
        }
    }
    
    function get_one_community($com_id){
        global $connection;
        $query="SELECT * from community_info where com_id =" . $com_id;
        $result=mysql_query($query, $connection);
        confirm_query($result);
        if ($row=mysql_fetch_array($result)){
            return $row;
        } else {
            return NULL;
        }
    }
    
    function get_all_coordinators(){
	global $connection;
	$query="SELECT * FROM coordinator_info";
	$result=mysql_query($query, $connection);
	confirm_query($result);
	return $result;
    }
    
    function get_one_coordinator($coord_id){
        global $connection;
        $query="SELECT * from coordinator_info where coord_id =" . $coord_id;
        $result=mysql_query($query, $connection);
        confirm_query($result);
        if ($row=mysql_fetch_array($result)){
            return $row;
        } else {
            return NULL;
        }
    }
	function get_all_projects(){
	global $connection;
	$query="SELECT * FROM project_info";
	$result=mysql_query($query, $connection);
	confirm_query($result);
	return $result;
    }
    
    function get_one_project($proj_id){
        global $connection;
        $query="SELECT * from project_info where PROJ_ID =" . $proj_id;
        $result=mysql_query($query, $connection);
        confirm_query($result);
        if ($row=mysql_fetch_array($result)){
            return $row;
        } else {
            return NULL;
        }
    }
	    function get_setid($setname){
        global $connection;
        $query="SELECT * from settlement_info where set_name =" . "\"" . $setname . "\" ";
        $result=mysql_query($query, $connection);
        confirm_query($result);
        if ($row=mysql_fetch_array($result)){
            return $row;
        } else {
            return NULL;
        }
    }
		function get_setname($setid){
        global $connection;
        $query="SELECT * from settlement_info where set_id =" . "\"" . $setid . "\" ";
        $result=mysql_query($query, $connection);
        confirm_query($result);
        if ($row=mysql_fetch_array($result)){
            return $row;
        } else {
            return NULL;
        }
    }
    
    function redirect_to($location){
        if($location!=NULL){
            header("Location: {$location}");
            exit;
        }
    }
    
    function mysql_prep($value){
        $magic_quotes_active=get_magic_quotes_gpc();
        $new_enough_php=function_exists("mysql_real_escape_string");
        if($new_enough_php){
            if($magic_quotes_active){$value=stripslashes($value);}
            $value=mysql_real_escape_string($value);
        }else {
            if(!$magic_quotes_active){ $value=addslashes($value); }
        }
        return $value;
    }
    
?>