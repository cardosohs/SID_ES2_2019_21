## SCRIPT PARA A MAQUINA DO AUDITOR ##
# esta informação é obrigatória para a correta exportação
$OutputEncoding = [System.Text.Encoding]::Unicode
[Console]::OutputEncoding=[System.Text.Encoding]::Unicode
$PSDefaultParameterValues = @{ '*:Encoding' = 'UTF8' }

#remove jobs que já estejam ativos
Unregister-Event -SourceIdentifier FileCreated -ErrorAction SilentlyContinue
Write-host "activa escuta na pasta destino"
#definição de variáveis
#path
$folder = 'C:\destino\'
$folderUx='C:/destino/'
$destinationFolder='C:\origem\'
$scriptFolder=$folder+"scripts\"

#auxiliar functions
#write status to DB
$insert=$scriptFolder+"insert-report" #returns [int] param([string]$tabela,[string]$processo, [int]$resultado )
#wait for write permitions on file
$wait=$scriptFolder+"wait-unlock" #returns [boolen] param([string]$fileParam )
#imports file into database
$import=$scriptFolder+"import-XML"
#Timer for replies
$ReplyTimer=$scriptFolder+"Start-timeout"
#deletes a file
$deleteFile = $scriptFolder+"delete-file" #param([string]$file)

#file watcher job
$filter = '*.*'                             
$RequestFile='request'
$replyFile='reply'
$waitFile='waiting'

#database connection
$dbServer="localhost"
$logdatabase="g21destino"
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


#Write-host "Activa um Job na pasta do Auditor"
$fsw = New-Object IO.FileSystemWatcher $folder, $filter -Property @{
 IncludeSubdirectories = $false              
 NotifyFilter = [IO.NotifyFilters]'FileName'
}
$onCreated = Register-ObjectEvent $fsw Created -SourceIdentifier FileCreated -Action {
    #Wait-Debugger
    $file = $Event.SourceEventArgs.Name
    #Write-Host "The file '$file' was found!" 
      
    $prefixo=($file.Split('_'))[0]
    $tabela=($file.Split('_'))[1]+"_"+((($file.Split('_'))[2]).Split("."))[0]
    $extencao = ($file.Split("."))[1]
    write-host $prefixo + $tabela + $extencao
####
#se for um pedido
#copia o ficheiro REQUEST para a maindatabase
####
 #Wait-Debugger
    if($prefixo -eq $RequestFile){
        $processo="send request"
        
        # $s=New-PSSession -ComputerName $IP
        Try{
            copy-Item ($folder+$file) -Destination $destinationFolder  -ErrorAction Stop #-ToSession $s
            $resultado=1
            Write-host "Request enviado"
            Write-host "criado ficheiro temporário"  $folder($waitFile+"_"+$tabela+".csv")
            New-Item -Path $folder -Name ($waitFile+"_"+$tabela+".csv") -ItemType "file" -Value (Get-Date -Format "yyyy-MM-dd HH:mm:ss")
            
        }
        catch { ##erro ao enviar ficheiro
        Write-Host "falhou o envio "$error[0]
        $resultado = 0
        }
        if (&$wait ($file)){
                Remove-Item ($folder+$file) -ErrorAction Continue
                write-host $file "apagado"
                }
            else{Write-host "falha ao apagar ficheiro"}

        #apagar o ficheiro asincronamente
        <#
        #$j=
        Start-Job -ScriptBlock{
            param($file)
            Invoke-Expression ("C:\Destino\Scripts\delete-file.ps1 ("+$file+")")
            } -name 'Delete Request' -ArgumentList $file
           
          # debug-job $J   
        #>
        #escreve na tabela log_migração o resultado da operação;
        # Write-host "Inserindo o resultado da operação "
        &$insert  $tabela $processo $resultado
        Write-host "Fim do " $processo " foi " $resultado
    } #Fim de request#

####
#Se for um XML importa para a tabela respectiva
####
    
   
    elseif ( ($prefixo -eq $replyFile)-and ($extencao -eq "xml")){
        $processo="receive reply"
        

        write-host "detetada resposta para a tabela "$tabela
   
        #aguarda que o ficheiro esteja disponivel para escrita
        if (&$wait $file){
            #escrever readable =1
            &$insert $tabela $processo 1
            #apagar o marcador de espera
            Remove-Item ($folder+($waitFile+"_"+$tabela+".csv")) -Force
            Write-Host "apagando" ($folder+($waitFile+"_"+$tabela+".csv"))
            #importXML
            &$import $tabela    
            }
        else{
            #escrever readable =0
            &$insert $tabela $processo 0
        }
    } #fim de reply
   <#
    elseif($prefixo -eq $waitFile){
        #inicia a contagem do tempo de espera
        Invoke-Command -ScriptBlock{  
            param([string] $f)
            &$ReplyTimer @PSBoundParameters
            } -AsJob -Verbose -ArgumentList $file 
      
       <# Start-Job -ScriptBlock {
        &$ReplyTimer $file
        } -Name ("timer_"+$tabela) #-ArgumentList $ReplyTimer, $file
        
        
    }#>
    
    else{
    write-host "ficheiro não identificado"
    }


}#fim de gancho





#desliga o agente
#Unregister-Event -SourceIdentifier FileCreated
#$connection.Close()

