<?php

include_once("connect.php");


//echo "set";

$cname=$_POST['name'];
$phoneno=$_POST['phno'];
$emailid=$_POST['email'];
$password=$_POST['pass'];
$confirmpassword=$_POST['cpass'];
$username=$_POST['uname'];

if( $password == $confirmpassword ){
$query= "insert into customer values(null,'$cname','$phoneno','$emailid','$password','$username')";

$result =  @mysql_query($query);
echo "success" ;
}
else
{
	echo "failed" ;
}
?>



 