<?php
$url="127.0.0.1";
$database="g21origem";
$username = $_POST['username'];
$password = $_POST['password'];
$conn = mysqli_connect($url,$username,$password,$database);
$sql = "select idCultura from Cultura where idInvestigador=(select idInvestigador from Investigador where email='$username')";
$result = mysqli_query($conn, $sql);
$response["culturas"] = array();
if ($result){
    if (mysqli_num_rows($result)>0){
        while($r=mysqli_fetch_assoc($result)){
            $ad = array();
            $ad["idCultura"] = $r['idCultura'];
            array_push($response["culturas"], $ad);
        }
    }
}


$json = json_encode($response["culturas"]);
echo $json;
mysqli_close ($conn);