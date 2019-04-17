<?php
$url="127.0.0.1";
$database="g21origem";
$investigador='alex';
$username='root';
$password='';
$conn = mysqli_connect($url,$username,$password,$database);
$iduser = "select idInvestigador from Investigador where email='".$investigador."'";
$sql = "select idCultura from Cultura where idInvestigador='.$iduser.'";
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