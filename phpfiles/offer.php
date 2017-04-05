<?php
include_once("connect.php");

$offer = $_POST['oname'];



$sql="insert into offer values(null,'$offer')";
mysql_query($sql,$connect); //to fire above query

echo "value inserted";


?>




