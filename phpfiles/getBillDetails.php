<?php
	include("connect.php");
	$billid=$_REQUEST['partialState'];
	$totalBill = 0;
	$result=mysqli_query($connect,"SELECT * FROM productlist inner join purchases on productlist.pid = purchases.pid where purchases.billid='$billid'");
	
	echo "<table><tr><th>PRODUCT NAME</th><th>QUANTITY</th><th>TOTAL PRICE</th><th>TOTAL WEIGHT</th></tr>
			<tr><td></td><td></td><td></td><td></td></tr>";
	if(mysqli_num_rows($result)){
		while($row= mysqli_fetch_array($result)){
			echo '<tr><td>'.$row['pname'].'</td>
					  <td>'.$row['count'].'qty</td>
					  <td>Rs'.$row['price']*$row['count'].'</td>
					  <td>'.$row['weight']*$row['count'].'gram</td>
				  </tr>';
			$totalBill = $totalBill + ($row['price']*$row['count']);
		}
	}else{
		echo '<tr><td>no such bill id</td></tr>';
	}
	echo "</table>";

	echo '<div class="cf"><h4>Total Bill:</h4><h1>'.$totalBill.'</h1></div>';
?>