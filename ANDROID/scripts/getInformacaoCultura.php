<?php
$url="127.0.0.1";
$database="g21origem";
$username = $_POST['username'];
$password = $_POST['password'];
$idCultura = $_POST['idCultura'];
$conn = mysqli_connect($url,$username,$password,$database);

$sql = "Select NomeCultura,DescricaoCultura from cultura where idCultura='$idCultura'";
$result = mysqli_query($conn, $sql);
$response["infocultura"] = array();
if ($result){
    if (mysqli_num_rows($result)>0){
        while($r=mysqli_fetch_assoc($result)){
            $ad = array();
            $ad["NomeCultura"] = $r['NomeCultura'];
            $ad["DescricaoCultura"] = $r['DescricaoCultura'];
            array_push($response["infocultura"], $ad);
        }
    }
}


$json = json_encode($response["infocultura"]);
echo $json;
mysqli_close ($conn);