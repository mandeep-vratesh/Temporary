<?php
include_once("connect.php");
$productname=$_POST["pname"];
$productprice = $_POST['price'];
$productfloor = $_POST['floor'];
$productside = $_POST['side'];
$productsec = $_POST['section'];


$sql="insert into productlist values(null,'$productname','$productprice','$productfloor','$productside','$productsec')";
mysql_query($sql,$connect); //to fire above query

echo "values inserted";
?>

