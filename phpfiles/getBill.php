<?php
	include("connect.php");
	$partialStatus=$_REQUEST['partialState'];

	$status=mysqli_query($connect,"SELECT DISTINCT(billid) FROM purchases WHERE billid LIKE '%$partialStatus%'");
	if($partialStatus=="")
		{}
	else if(mysqli_num_rows($status)==0)
		{
			echo '<div style="border-radius:3px;background-color:#aaa;">No such purchase found</div>';
		}
	else	
	{
		while($row=mysqli_fetch_array($status))
		{
			echo '<div style="margin-top:5px;border-radius:3px;background-color:#111;width:auto;height:20px" value="'.$row['billid'].'">'.$row['billid'].'</div>';
		}
	}
?>