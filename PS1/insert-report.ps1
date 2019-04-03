
#faz a inserção na tabela log_migracao dos eventos
param([string]$tabela,[string]$processo, [int]$resultado )
Write-host "inserir relato..." $processo "para a tabela " $tabela "foi" $resultado
#Wait-Debugger
#definições de sistema
$user="root"
$logdatabase="g21destino"
$server="localhost"
$agora=Get-Date -Format "yyyy-MM-dd HH:mm:ss"
$query="INSERT INTO "+$logdatabase +".log_migracao(datahoraMig,tabela, processo, resultado) values(`'"+$agora+"`',`'"+$tabela+"`',`'"+ $processo + "`',"+$resultado +");"

#configuração da ligação SQL
$ConnectionString= "Server="+$server+";Uid="+$user+";database="+$logdatabase
[void][System.Reflection.Assembly]::LoadWithPartialName("MySql.Data")
$connection = New-Object MySql.Data.MySqlClient.MySqlConnection
$connection.ConnectionString = $ConnectionString
$connection.Open() 

$oMYSQLCommand = New-Object MySql.Data.MySqlClient.MySqlCommand
$oMYSQLCommand.Connection=$connection
    
#executar o comando $query
$SQLcommand=New-Object MySql.Data.MySqlClient.MySqlCommand
$SQLcommand.Connection=$connection
$SQLcommand.CommandText=$query
$new=$SQLcommand.ExecuteNonQuery()
$new

