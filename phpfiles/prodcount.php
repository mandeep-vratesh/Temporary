<?php
include_once("connect.php");

$prodid = $_POST['pid'];

$prodcount = $_POST['count'];


$sql="insert into prodcount values('$prodid','$count')";
mysql_query($sql,$connect); //to fire above query

echo "value inserted";
?>
<html>
<head>
</head>

<body>
<form action="<?PHP $_PHP_SELF ?>" method="POST">
Product id: <input type="text" name="pid" maxlength="10">
<br>
<br> 

count: <input type="text" name="count" maxlength="100">
<br>
<br>
<input type="button" value="Add Offer">

</form>
</body>


</html>
