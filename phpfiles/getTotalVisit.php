<?php

include('connect.php');

$id = $_REQUEST['id'];
$visits = 0;
$totalPurchase = 0;

$sql = "SELECT COUNT(purchaseid) AS count FROM purchases WHERE cid = $id AND DATEDIFF(NOW(),timestamp) <= 30";
$result = mysqli_query($connect, $sql);
$row = mysqli_fetch_array($result);

echo $row['count'];

?>