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

$id = $_REQUEST['id'];

$sql="SELECT purchases.timestamp AS time_of_purchase, productlist.pname AS product_name, purchases.count AS qty, (purchases.count * productlist.price) AS cost FROM `purchases`,`customer`,`productlist` WHERE purchases.pid = productlist.pid AND purchases.cid = '$id' ORDER BY time_of_purchase DESC";
$result=mysqli_query($connect,$sql);
echo "<center><h4>Your all shopping.</h4></center>";
while($row=mysqli_fetch_array($result))
{
	
	echo'<div class="alert alert-info">Date and Time: '.$row['time_of_purchase'].'<br/>Product Name: '.$row['product_name'].'<br/>Quantity: '.$row['qty'].'<br/>Total Cost: '.$row['cost'].'</div>';

}

?>
</body>
</html>