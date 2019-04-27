#deletes a <file> in the work folder
param([string]$file)

#path
$folder = 'C:\destino\'
$scriptFolder=$folder+"scripts\"

#wait for write permitions on file
$wait=$scriptFolder+"wait-unlock" #returns [boolen] param([string]$fileParam )

if (&$wait ($file)){
                Remove-Item ($folder+$file) -ErrorAction Continue
                write-host $file "apagado"
                }
            else{Write-host "falha ao apagar ficheiro"}