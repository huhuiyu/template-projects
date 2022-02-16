# ..\scripts\gradle-cmd.ps1
..\scripts\java-home.ps1
..\scripts\gradle-home.ps1
gradle clean build --warning-mode all
$currentyDir = Split-Path -Parent $MyInvocation.MyCommand.Definition;
$serverProjectPath = "D:\git\code.aliyun\huhuiyu.top.server\java\"
Copy-Item $currentyDir\build\libs\springboot-template.war $serverProjectPath
Set-Location $serverProjectPath
start .
Set-Location $currentyDir