<?php

include('connect.php');

$id = $_REQUEST['id'];

$sql = "SELECT COUNT(purchaseid) AS count FROM purchases WHERE cid = '$id' AND DATEDIFF(NOW(),timestamp) <= 30";
$result = mysqli_query($connect, $sql);
$row = mysqli_fetch_array($result);
$return['visits'] = $row['count'];


$sql = "SELECT SUM(purchases.count * productlist.price) AS total FROM purchases,productlist WHERE purchases.pid = productlist.pid AND purchases.cid = $id AND DATEDIFF(NOW(),purchases.timestamp) <= 30";
$result = mysqli_query($connect, $sql);
$row = mysqli_fetch_array($result);
$return['total'] = $row['total'];

echo json_encode($return);
?>