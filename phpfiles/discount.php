<?php
include_once("connect.php");

$discount = $_POST['dname'];



$sql="insert into discount values(null,'$discount')";
mysql_query($sql,$connect); //to fire above query

echo "value inserted";


?>

