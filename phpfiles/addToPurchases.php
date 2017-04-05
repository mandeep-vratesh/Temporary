<?php

include('connect.php');

$billid = $_POST['billid'];
$cid = $_POST['cid'];
$pid = $_POST['pid'];
$counts = $_POST['counts'];

// $billid = "jkhlefhjbljabsbjd";
// $cid = "1";
// $pid = "[1]";
// $counts = "[2]";

$pid = substr($pid, 1, strlen($pid)-2);
$counts = substr($counts, 1, strlen($counts)-2);

$pid = explode(",", $pid);
$counts = explode(",", $counts);

$working = 0;
for($i = 0; $i<sizeof($pid);++$i){
	$sql = "INSERT INTO purchases(pid,cid,timestamp,count,billid) VALUES('$pid[$i]','$cid',NOW(),'$counts[$i]','$billid')";
	if(mysqli_query($connect, $sql)){
		$working++;
	}else{
		echo mysqli_error($connect);
	}
}

if($working > 0){
	echo "success".$working;
}else{
	echo "failed";
}
// echo $billid." ".$cid." ".$pid." ".$counts;

?>
