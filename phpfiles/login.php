<?php

header('Content-Type: application/json');
include "connect.php";

	$username=$_REQUEST['username'];
	$password=$_REQUEST['password'];

$sql="SELECT * FROM customer where cname='$username' AND pass='$password' ";
$result=mysqli_query($connect,$sql);
if(mysqli_num_rows($result))
{
	$row = mysqli_fetch_array($result);
	$id = $row['cid'];
	$return['success']=$row['cid'];	
}else{
	$return['success']=-1;	
}


//returns the number of visits in last 30 days
$sql = "SELECT COUNT(purchaseid) AS count FROM purchases WHERE cid = '$id' AND DATEDIFF(NOW(),timestamp) <= 30";
$result = mysqli_query($connect, $sql);
$row = mysqli_fetch_array($result);
$return['visits'] = $row['count'];

//returns sum of all the purchases in last 30 days
$sql = "SELECT SUM(purchases.count * productlist.price) AS total FROM purchases,productlist WHERE purchases.pid = productlist.pid AND purchases.cid = $id AND DATEDIFF(NOW(),purchases.timestamp) <= 30";
$result = mysqli_query($connect, $sql);
$row = mysqli_fetch_array($result);
$return['total'] = $row['total'];

//returns all the purchases ever
$sql = "SELECT DISTINCT(purchases.pid) FROM purchases,productlist WHERE cid = '$id' AND productlist.israre = 1";
$result = mysqli_query($connect, $sql);
$return['rareProductIds'] = array();
while($row = mysqli_fetch_array($result)){
	array_push($return['rareProductIds'], $row['pid']);
}
echo json_encode($return);
?>