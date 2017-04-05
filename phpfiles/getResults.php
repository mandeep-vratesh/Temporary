<?php
	include("connect.php");
	$partialStatus=$_POST['partialState'];
	$status=mysqli_query($connect,"SELECT * FROM productlist WHERE pname LIKE '%$partialStatus%'");
	if($partialStatus=="")
		{}
	else if(mysqli_num_rows($status)==0)
		{
			echo '<div style="border-radius:3px;background-color:#aaa;">Oops! We dont seem too have this product.</div>';
		}
	else	
	{
		while($row=mysqli_fetch_array($status))
		{
			echo '<div style="border-radius:3px;background-color:#aaa;width:auto;height:100px;"></br>Name: '
					.$row['pname'].
				 '</br>Floor: '
				 	.$row['floor'].
				 '<br/>Side: '
				 	.$row['side'].
				 '<br/>Section: '
				 	.$row['section'].
				 '</br></br></div>';
		}
	}
?>