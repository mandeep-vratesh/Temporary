<?php

include('connect.php');

$id = $_REQUEST['id'];
$visits = 0;
$totalPurchase = 0;

$sql = "SELECT SUM(purchases.count * productlist.price) AS total FROM purchases,productlist WHERE purchases.pid = productlist.pid AND purchases.cid = $id AND DATEDIFF(NOW(),purchases.timestamp) <= 30";
$result = mysqli_query($connect, $sql);
$row = mysqli_fetch_array($result);

echo $row['total'];

?>