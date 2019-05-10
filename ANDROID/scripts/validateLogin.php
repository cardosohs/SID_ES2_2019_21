	<?php
	$url="127.0.0.1";
	$database="g21origem";
	$username = $_POST['username'];
	$password = $_POST['password'];
    $conn = mysqli_connect($url,$username,$password,$database);
	$response["valid"] = array();
	$json = json_encode($response["valid"]);
	echo $json;
	mysqli_close ($conn);