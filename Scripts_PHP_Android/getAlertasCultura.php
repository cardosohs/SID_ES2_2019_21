<?php
$url="127.0.0.1";
$database="g21origem";
$username='root';
$password='';
$date='2019-04-16';
$idCultura=1;
$conn = mysqli_connect($url,$username,$password,$database);
$sql = "select * from alertas,cultura where alertas.DataHora >= $date and cultura.idcultura=getCulturasOfUser";
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