<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<style type="text/css">
	body{
		background-color: #000;
		color: #fff;
	}
	div{
		overflow: none;
		margin-left: 1%;
		margin-right: 1%;
		}</style>
</head>
<?php
include "connect.php";

$sql="SELECT * FROM offer WHERE 1";
$result=mysqli_query($connect,$sql);
echo "<center><h4>OFFERS !</h4></center>";
while($row=mysqli_fetch_array($result))
{
	
	echo'<div class="alert alert-info"><strong>'.$row['offerid'].'('.$row['offer'].'):</strong><br></div>';

}

?>
</body>
</html>