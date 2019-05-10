<?php
$url="127.0.0.1";
$database="g21origem";
$username = $_POST['username'];
$password = $_POST['password'];
$date = $_POST['date'];
$idCultura = $_POST['idCultura'];
$conn = mysqli_connect($url,$username,$password,$database);
$sql = "select * from alertas,cultura,variaveis where datediff(alertas.DataHora, '$date') = 0 and cultura.idcultura='$idCultura' and alertas.NomeVariavel=variaveis.NomeVariavel";
$result = mysqli_query($conn, $sql);
$response["alertascultura"] = array();
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
            array_push($response["alertascultura"], $ad);
        }
    }
}


$json = json_encode($response["alertascultura"]);
echo $json;
mysqli_close ($conn);