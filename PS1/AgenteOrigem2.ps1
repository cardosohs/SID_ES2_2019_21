## SCRIPT PARA A MAQUINA 'ORGIGEM' ##
 # esta informação é obrigatória para a correta exportação
$OutputEncoding = [System.Text.Encoding]::Unicode
[Console]::OutputEncoding=[System.Text.Encoding]::Unicode
$PSDefaultParameterValues = @{ '*:Encoding' = 'UTF8' }

try{
Unregister-Event -SourceIdentifier FileOnOrign -ErrorAction SilentlyContinue
}
catch {
write-host "não havia ganchos"
}
#definição de variáveis
$folderOrigin = 'C:\Origem\'
$filter = '*.*'                           
$destinationFolder='C:\destino\'
$database="g21origem"
#$IP='127.99.0.1'
$RequestFile='request'
$ConnectionString= "Server=localhost;Uid=root; database="+$database 
#$SP_GetData="AtualizaTabela"
$user = "-uroot"
$mysqlPaht = "C:\xampp\mysql\bin\mysql.exe " #--default-character-set=utf8"

#o nome do ficheiro muda conforme a tabela a terminação não
$ResponseExtension=".xml"
$responsePrefix="reply_"
#$s=New-PSSession -ComputerName $IP

Write-host "Activa escuta na pasta Origem"
$fsw = New-Object IO.FileSystemWatcher $folderOrigin, $filter -Property @{
 IncludeSubdirectories = $false              # <-- set this according to your requirements
 NotifyFilter = [IO.NotifyFilters]'FileName, LastWrite'
}
$onCreated = Register-ObjectEvent $fsw Created -SourceIdentifier FileOnOrign -Action {
 #$path = $Event.SourceEventArgs.FullPath
 $name = $Event.SourceEventArgs.Name
 
 #Se for o ficheiro $RequestFile Obtem dados e exporta XML
 $prefixo=($name.Split('_'))[0]

    if($prefixo -eq $RequestFile){
Write-host "encontrado Request"
 #Start-Sleep -Seconds 1 -Verbose
 
try{
Write-host 'obter dados do CSV'$name
$data=Import-csv -path $FolderOrigin$name -Header "tabela","inicio","fim" -Delimiter "`t" -ErrorVariable $erroImport_csv
remove-item $FolderOrigin$name 

$ResponseFile= $responsePrefix+$DATA.tabela + $ResponseExtension
if ($data.inicio -ne '/N'){
$swich='--xml -e "SELECT * FROM '+ $database+'.'+$DATA.tabela + " where dataHoraLog >='"+ $data.inicio + "' and dataHoraLog<='"+$data.fim +"'"+'" >'
}
elseif ($data.inicio -eq '/N'){
Write-host "desde do inicio"
$swich='--xml -e "SELECT * FROM '+ $database+'.'+$DATA.tabela + "' and dataHoraLog<='"+$data.fim +"'"+'" >'
}
else{
write-host "erro na determinação do intervalo de tempo"
return 0;
}
 #Executa o Dump
$cmd= $mysqlPaht+" "+ $user+" "+ $swich+" "+ $folderOrigin  + $ResponseFile

try{
#Write-Host "vai invocar " $cmd
Invoke-Expression $cmd  -ErrorVariable $erroInvoke_expression
#invoke-command -ScriptBlock {Invoke-Expression $cmd} -AsJob -NoNewScope
}
Catch{
Write-Host "erro na exportação para ficheiro"
write-host $erroInvoke_expression
write-host $cmd
Write-Host "---------------"
Write-Host $error[0]
} 

}
catch {
Write-Host "erro na importação de CSV"
write-host $erroImport_csv
Write-Host "---------------"
Write-Host $error[0]
}

 }

#Se for um XML copia o ficheiro para o destino
if ($name.EndsWith(".xml")){
Write-host "detectado XML"$name
$path= $FolderOrigin+$name
#write-host $path
#write-host $destinationFolder

#aguarda que o ficheiro esteja disponivel para escrita
$IsLocked = $True
$retry=0
while (($IsLocked) -and ($retry -lt 9)){
    Try {
        $FileStream = [System.IO.File]::Open($path,'Open','Write')
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

#tenta copiar o ficheiro para o destino
Try{

Copy-Item $path -Destination $destinationFolder -Force:$true -errorAction stop -ErrorVariable $e #-ToSession $s 
 #Move-Item $path -Destination $destination -Force -Verbose # Force will overwrite files with same name~Remove-Item $path
write-host "Ficheiro copiado"
}
catch {
Write-host "erro ao enviar ficheiro "$e
write-host $error[0]
}

#desliga a sessão no outro computador
#Remove-PSSession $s

}
}


#desliga o agente
#Unregister-Event -SourceIdentifier FileOnOrign
