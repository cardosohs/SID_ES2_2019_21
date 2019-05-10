	<?php
	$url="127.0.0.1";
	$database="g21origem";
    $username = $_POST['username'];
    $password = $_POST['password'];
    $conn = mysqli_connect($url,$username,$password,$database);
	$sql = "select DataHoraMed,ValorMedicaoLuz from medicoesluz where DataHoraMed >= now() - interval 5 minute";
	$result = mysqli_query($conn, $sql);
	$response["medicoes"] = array();
	if ($result){
		if (mysqli_num_rows($result)>0){
			while($r=mysqli_fetch_assoc($result)){
				$ad = array();
                $ad["DataHoraMed"] = $r['DataHoraMed'];
				$ad["ValorMedicaoLuz"] = $r['ValorMedicaoLuz'];
				array_push($response["medicoes"], $ad);
			}
		}	
	}
	
	
	$json = json_encode($response["medicoes"]);
	echo $json;
	mysqli_close ($conn);