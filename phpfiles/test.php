<?php
include('connect.php');
//get post data
$id = $_REQUEST['productid'];

$return = array();

$sql = "SELECT * FROM productlist WHERE pid = '$id'";

$result = mysqli_query($connect, $sql);

while($row = mysqli_fetch_array($result)){
	$return['product_name'] = $row['pname'];
	$return['product_price'] = $row['price'];
	$return['product_weight'] = $row['weight'];
}

echo json_encode($return);
?>