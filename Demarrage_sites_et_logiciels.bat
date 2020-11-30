REM LANCEMENT AUTOMATIQUE SITES WEB
@echo off
SET BROWSER=firefox-83.0.exe

start /min %BROWSER% http://blabla.fr
SET WAIT_TIME=3
timeout %WAIT_TIME%

exit
