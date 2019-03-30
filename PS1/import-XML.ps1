 #importXMLimporta a tabela XML
 param([String] $tabela)

<#
$OutputEncoding = [System.Text.Encoding]::Unicode
[Console]::OutputEncoding=[System.Text.Encoding]::Unicode
$PSDefaultParameterValues = @{ '*:Encoding' = 'utf16' }
#>
#remove jobs que já estejam ativos
#Unregister-Event -SourceIdentifier FileCreated -ErrorAction SilentlyContinue

#definição de variáveis
#path
$folder = 'C:\destino\'
$folderUx='C:/destino/'
$destinationFolder='C:\origem\'
$scriptFolder=$folder+"scripts\"
$file="reply_"+$tabela+".xml"

#auxiliar functions
#write status to DB
$insert=$scriptFolder+"insert-report" #returns [int] param([string]$tabela,[string]$processo, [int]$resultado )
#wait for write permitions on file
$wait=$scriptFolder+"wait-unlock" #returns [boolen] param([string]$fileParam )

#database connection
$dbServer="localhost"
$logdatabase="logsg21db"
$dbUser="root"
#$IP='127.0.0.10'

#configuração da ligação SQL
$ConnectionString= "Server="+$dbServer+";Uid="+$dbUser+"; database="+$logdatabase
# load MySQL driver and create connection
[void][System.Reflection.Assembly]::LoadWithPartialName("MySql.Data")
$connection = New-Object MySql.Data.MySqlClient.MySqlConnection
$connection.ConnectionString = $ConnectionString
#Write-Verbose "Open Database Connection"
$connection.Open()
$oMYSQLCommand = New-Object MySql.Data.MySqlClient.MySqlCommand
$oMYSQLCommand.Connection=$connection

 try{ 
                $processo="Update database"
                $query="LOAD XML INFILE '"+$folderUx+$file+"' INTO TABLE "+$logdatabase+"."+$tabela+";"
                write-host $query
                $updateCOD = New-Object MySql.Data.MySqlClient.MySqlCommand($query, $connection)
                $updateCOD.CommandText
                $iReturn=$updateCOD.ExecuteNonQuery() 
                write-host "new rows "$iReturn
                #verifica se houve valores inseridos
                if ($ireturn -gt 0){
                    $resultado=1
                    
                }
                else{
                $resultado=0
                }
                
                Write-host "resultado da operação " $processo " foi " $resultado
                &$insert  $tabela $processo $resultado
                
                if (&$wait ($file)){
                   Remove-Item ($folder+$file) -ErrorAction Continue
                   write-host $file "deleted"
                    }
                else{Write-host "falha ao apagar ficheiro"}

                }
            catch{
                # resultado da operação;
                $resultado=0
                Write-host "resultado da operação " $processo " foi " $resultado
                &$insert $tabela $processo $resultado
                
                write-host "erro de importacao na tabela "$tabela
                write-host $Error[0].PSMessageDetails
                write-host $_.Exception.Message
                #write-host $sql
                }