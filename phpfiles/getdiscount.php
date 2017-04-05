<?php
include_once("connect.php");

$query="select * from discount";

$result= mysql_query($query,$connect); //to fire above query

$rows= array();

while($row = mysql_fetch_assoc($result)){
	array_push($rows,$row);
}
	echo json_encode($rows);



?>

