## SCRIPT PARA A MAQUINA DO AUDITOR ##
 # esta informação é obrigatória para a correta exportação
$OutputEncoding = [System.Text.Encoding]::Unicode
[Console]::OutputEncoding=[System.Text.Encoding]::Unicode
$PSDefaultParameterValues = @{ '*:Encoding' = 'utf16' }

try{
Unregister-Event -SourceIdentifier FileCreated -ErrorAction SilentlyContinue
}catch{
write-host "não havia ganchos"
}
#definição de variáveis
$folder = 'C:\destino\'
$folderUx='C:/destino/'
$filter = '*.*'                             # <-- set this according to your requirements
$destinationFolder='C:\origem\'
#$IP='127.0.0.10'
$RequestFile='request'
$logdatabase="logsg21db"

#configuração da ligação SQL
$ConnectionString= "Server=localhost;Uid=root; database="+$logdatabase

# load MySQL driver and create connection
[void][System.Reflection.Assembly]::LoadWithPartialName("MySql.Data")
$connection = New-Object MySql.Data.MySqlClient.MySqlConnection
$connection.ConnectionString = $ConnectionString
Write-Verbose "Open Database Connection"
$connection.Open()


$oMYSQLCommand = New-Object MySql.Data.MySqlClient.MySqlCommand
$oMYSQLCommand.Connection=$connection



Write-host "Activa um anzol na pasta do Auditor"

$fsw = New-Object IO.FileSystemWatcher $folder, $filter -Property @{
 IncludeSubdirectories = $false              # <-- set this according to your requirements
 NotifyFilter = [IO.NotifyFilters]'FileName, LastWrite'
}
$onCreated = Register-ObjectEvent $fsw Created -SourceIdentifier FileCreated -Action {
 #$path = $Event.SourceEventArgs.FullPath
 $name = $Event.SourceEventArgs.Name
 #$changeType = $Event.SourceEventArgs.ChangeType
 #$timeStamp = $Event.TimeGenerated
#  Write-Host "The file '$name' was $changeType at $timeStamp" #for tests
 
 
####
#se for um pedido
#copia o ficheiro REQUEST para a maindatabase
####
$prefixo=($name.Split('_'))[0]
#write-host $prefixo
    if($prefixo -eq $RequestFile){
  #     Write-Host "$prefixo -eq $RequestFile"
     # $s=New-PSSession -ComputerName $IP
        Try{
            copy-Item ($folder+$name) -Destination $destinationFolder  -ErrorVariable $e #-ToSession $s
            remove-item ($folder+$name) -ErrorVariable $e
        }
        catch { ##erro ao enviar ficheiro
        Write-Host $e 
        
        }

      # Remove-PSSession $s
} #Fim de request#

####
#Se for um XML importa para a tabela respectiva
####
    $extencao = ($name.Split("."))[1]
    if ($extencao -eq "xml"){
        $tabela = ($name.Split("."))[0]

        write-host "detetada a tabela "$tabela
   
   #aguarda que o ficheiro esteja disponivel para escrita
    $IsLocked = $True
    $retry=0
    while (($IsLocked) -and ($retry -lt 9)){
        Try {
            $FileStream = [System.IO.File]::Open($folder+$name,'Open','Write')
            $FileStream.Close()
            $FileStream.Dispose()
            $IsLocked = $False
            }
            catch{
            $IsLocked = $True
            Start-Sleep -Milliseconds 2
            $retry++
            }
    }
    write-host "foram necessárias "$retry " tentativas"


   
   
    try{
   
        $sql="LOAD XML INFILE '"+$folderUx+$name+"' INTO TABLE "+$logdatabase+"."+$tabela+";"
        $updateCOD = New-Object MySql.Data.MySqlClient.MySqlCommand($sql, $connection)
        $updateCOD.CommandText
        $iReturn=$updateCOD.ExecuteNonQuery() 
        Remove-Item $folder$name
        #$connection.Close()
###
       
       write-host "new rows "$iReturn
       }
       catch{
       write-host $Error[0].PSMessageDetails
       write-host $_.Exception.Message
       #write-host $sql
       }

}
}







#desliga o agente
#Unregister-Event -SourceIdentifier FileCreated
