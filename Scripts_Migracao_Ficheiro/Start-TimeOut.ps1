param([string] $file)

#timer
$timeout = 5 #s
 $processo="receive reply"

#folder
$folder="c:\destino\"
$tabela=($file.Split('_'))[1]+"_"+((($file.Split('_'))[2]).Split("."))[0]

#write status to DB
$insert=$scriptFolder+"insert-report" #returns [int] param([string]$tabela,[string]$processo, [int]$resultado )

#fica à espera do timeOut
Start-Sleep -Seconds $timeout

#Se o ficheiro ainda estiver presente dá erro
if(Test-Path -Path ($folder+$file)){
    &$insert $tabela $processo 0
    Remove-Item -Path ($folder+$file)
}
Write-host "exiting timeOut" $tabela