<?php
$url="127.0.0.1";
$database="g21origem";
$username = $_POST['username'];
$password = $_POST['password'];
$date= $_POST['date'];
$conn = mysqli_connect($url,$username,$password,$database);
$sql = "SELECT * FROM alertas WHERE datediff(alertas.DataHora, '$date') = 0 and (alertas.NomeVariavel='Luminosidade' OR alertas.NomeVariavel='Temperatura') ";
$result = mysqli_query($conn, $sql);
$response["alertasglobais"] = array();
if ($result){
    if (mysqli_num_rows($result)>0){
        while($r=mysqli_fetch_assoc($result)){
            $ad = array();
            $ad["DataHora"] = $r['DataHora'];
            $ad["NomeVariavel"] = $r['NomeVariavel'];
            $ad["LimiteInferior"] = $r['LimiteInferior'];
            $ad["LimiteSuperior"] = $r['LimiteSuperior'];
            $ad["ValorMedicao"] = $r['ValorMedicao'];
            $ad["Descricao"] = $r['Descricao'];
            array_push($response["alertasglobais"], $ad);
        }
    }
}


$json = json_encode($response["alertasglobais"]);
echo $json;
mysqli_close ($conn);