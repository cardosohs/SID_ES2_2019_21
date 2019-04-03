#aguarda que o ficheiro esteja disponivel para escrita
param([string]$fileParam )
write-host "waiting lock on "$fileParam
#timer
$timeout = 4 #s
$timelapse=1 #mms

#folder
$folder="c:\destino\"
$file=$folder+$fileParam


#initial state
$IsLocked = $True
$timer = [Diagnostics.Stopwatch]::StartNew()

#do it
while (($IsLocked) -and (($timer.Elapsed.TotalSeconds -lt $Timeout))){
    Try {
        $FileStream = [System.IO.File]::Open($file,'Open','Write')
        $FileStream.Close()
        $FileStream.Dispose()
        $IsLocked = $False
        }
        catch{
        $IsLocked = $True
        Start-Sleep -Milliseconds $timelapse
        $retry++
        }
}
if ($timer.Elapsed.TotalSeconds -ge $Timeout){
write-host "abandonando ficheiro " $file " apos "$timer.Elapsed.TotalSeconds " segundos"
$response= $false
}
else {
        Write-Host "ficheiro " $file " desbloqueado apos" $timerElapsed.Elapsed.TotalSeconds
        $response=$true
    }
    $response