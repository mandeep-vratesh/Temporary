<?php

$hostname="localhost"; //hostname
$username="root"; //username for database, while installation of xampp, phpinifile to chnge name & password
$password=""; //database password
$dbname="ebilling"; //database name

$connect = mysqli_connect($hostname,$username,$password)
or die("error connecting to database"); //make connection
mysqli_select_db($connect,$dbname); //select database
?>