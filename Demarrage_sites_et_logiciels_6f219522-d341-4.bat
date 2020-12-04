REM LANCEMENT AUTOMATIQUE SITES WEB
@echo off
start "" "D:\Java\ides\eclipse\eclipse.exe"
SET WAIT_TIME=3
timeout %WAIT_TIME%

SET BROWSER=firefox.exe

start /min %BROWSER% http://www.blabla.fr
SET WAIT_TIME=3
timeout %WAIT_TIME%

exit
