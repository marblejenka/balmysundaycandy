::#!
@echo off

set _CLASSPATH=
if "%_CLASSPATH%"=="" (
  for %%f in ("%BALMYSUNDAYCANDY_SCALA_HOME%\lib\*") do call :add_cpath "%%f"
  if "%OS%"=="Windows_NT" (
    for /d %%f in ("%BALMYSUNDAYCANDY_SCALA_HOME%\lib\*") do call :add_cpath "%%f"
  )
)

call scala -Denv.classpath="%_CLASSPATH%" -classpath %_CLASSPATH% %0 %*
goto :eof

:add_cpath
  if "%_CLASSPATH%"=="" (
    set _CLASSPATH=%~1
  ) else (
    set _CLASSPATH=%_CLASSPATH%;%~1
  )
goto :eof
::!#

balmysundaycandy.scalatool.client.BalmysundaycandyInterpreterRunner.run(args)
