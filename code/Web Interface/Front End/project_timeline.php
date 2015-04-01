<?php require_once("includes/session.php") ?>
<?php require_once("includes/functions.php") ?>
<?php require_once("includes/connection.php") ?>
<?php require_once("includes/session.php") ?>
<?php
		function create_xml(){
		global $connection;
		$color=array('#bae3ff','#beffbe','#fffd64','#E5FAE3','#ff9d95','#ebc194','#ac7bfb');
		$fp= fopen("event.xml", "w");
		fwrite($fp,"<data>");
		$query="SELECT event_id,time_from,time_to from timeline";
        $result=mysql_query($query, $connection);
        confirm_query($result);
		$j10=0;
		while($row=mysql_fetch_array($result))
		{
			fwrite($fp," <event ");
			$id[$j10]=$row['event_id'];
			$sd=$row['time_from'];
			$ed=$row['time_to'];
			fwrite($fp,"start=");
			fwrite($fp,"'");
			fwrite($fp,$sd);
			fwrite($fp,"'");
			fwrite($fp," end=");
			fwrite($fp,"'");
			fwrite($fp,$ed);
			fwrite($fp,"'");
			fwrite($fp," isDuration=");
			fwrite($fp,"'true'");
			fwrite($fp," title='");
			fwrite($fp,$id[$j10]);
			fwrite($fp,"'");
			for($k=$j10-1;$k>=0;$k--)
			{
				
				if($id[$j10]==$id[$k])
				{
					$color[$j10]=$color[$k];
					break;
				}
			}
			fwrite($fp," color='");
			fwrite($fp,$color[$j10]);
			fwrite($fp,"' textColor='#000000'");
			fwrite($fp," >");
			/*fwrite($fp,"&lt;ul&gt;&lt;font size='4'&gt;&lt;b&gt;Category:&lt;/b&gt;&lt;/font&gt;");
			fwrite($fp,$pc);
			fwrite($fp,"&lt;br /&gt;&lt;font size='4'&gt;&lt;b&gt;Donors:&lt;/b&gt;&lt;/font&gt;");
			fwrite($fp,$pd);
			fwrite($fp,"&lt;br /&gt;&lt;font size='4'&gt;&lt;b&gt;Settlement Covered:&lt;/b&gt;&lt;/font&gt;");
			fwrite($fp,$ps);
			fwrite($fp,"&lt;br /&gt;&lt;font size='4'&gt;&lt;b&gt;Milestones:&lt;/b&gt;&lt;/font&gt;");
			if($pm==NULL)
			{
				fwrite($fp,"No Milestones");
			}
			else
			{
				fwrite($fp,$pm);
			}*/
			fwrite($fp," </event>");
			$j10=$j10+1;				
		}
		fwrite($fp," </data>");
		}
    ?>