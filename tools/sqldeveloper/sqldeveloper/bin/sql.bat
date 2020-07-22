@echo off
REM ######################################################################## 
REM #  (@)sql.bat
REM #
REM #  Copyright 2014 by Oracle Corporation,
REM #  500 Oracle Parkway, Redwood Shores, California, 94065, U.S.A.
REM #  All rights reserved.
REM #
REM #  This software is the confidential and proprietary information
REM #  of Oracle Corporation.
REM # 
REM # NAME	sql.bat
REM #
REM # DESC 	This script starts SqlCli.
REM #
REM # AUTHOR bamcgill
REM #
REM # MODIFIED	
REM #	bamcgill	21/03/2014	Created
REM #   bamcgill    17/07/2014  Simplified classpaths	
REM #   bamcgill    11/12/2014  Renamed script and contents 
REM #   bamcgill    16/01/2015  Renamed script and contents 
REM #   bamcgill    05/02/2015  Added headless to STD_OPTS to allow use of internal X server.
REM #   totierne    16/10/2015  Put classpath on the end - to allow timesten jars  
REM #   bamcgill    08/06/2016  Allow JAVA_HOME to be set in env and add to path
REM #   bamcgill    09/06/2016  Allow JDBC to be set properly 8 thru 6
REM #   bamcgill    19/06/2016  Rework JAVA_HOME switches
REM #   doneill     07/04/2017  Allow CUSTOM_JDBC environment variable to used
REM #   bamcgill    16/10/2017  Enumerating all jars in SQL_HOME\lib
REM ########################################################################
REM # SQL_HOME=.

REM Switch codepage to UTF
chcp 65001  >nul 2>&1

SET SQL_HOME=%~dp0..
SET DEBUG=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=8000

REM If java home is not set, this is an opportunity to set it.
IF NOT DEFINED JAVA_HOME (
  REM SET JAVA_HOME="C:\work\sqldeveloper\jdk\jre"
  REM SET PATH="%JAVA_HOME%\bin;%PATH%"
) ELSE (
   REM if java home is set, add it to the path
   REM SET PATH="%JAVA_HOME%\bin;%PATH%"
)
set echo on
SET CPFILE=%SQL_HOME%\lib\antlr-runtime.jar;%CPFILE%
SET CPFILE=%SQL_HOME%\lib\jackson-databind.jar;%CPFILE%         
SET CPFILE=%SQL_HOME%\lib\oracle.sqldeveloper.sqlcl.jar;%CPFILE%  
SET CPFILE=%SQL_HOME%\lib\osdt_cert.jar;%CPFILE%
SET CPFILE=%SQL_HOME%\lib\commons-codec.jar;%CPFILE%        
SET CPFILE=%SQL_HOME%\lib\javax.json.jar;%CPFILE%               
SET CPFILE=%SQL_HOME%\lib\oraclepki.jar;%CPFILE%                  
SET CPFILE=%SQL_HOME%\lib\osdt_core.jar;%CPFILE%
SET CPFILE=%SQL_HOME%\lib\commons-logging.jar;%CPFILE%      
SET CPFILE=%SQL_HOME%\lib\jline.jar;%CPFILE%                    
SET CPFILE=%SQL_HOME%\lib\orai18n-collation.jar;%CPFILE%          
SET CPFILE=%SQL_HOME%\lib\stringtemplate.jar;%CPFILE%
SET CPFILE=%SQL_HOME%\lib\httpclient.jar;%CPFILE%           
SET CPFILE=%SQL_HOME%\lib\jsch.jar;%CPFILE%                     
SET CPFILE=%SQL_HOME%\lib\orai18n-mapping.jar;%CPFILE%            
SET CPFILE=%SQL_HOME%\lib\xdb6.jar;%CPFILE%
SET CPFILE=%SQL_HOME%\lib\httpcore.jar;%CPFILE%             
SET CPFILE=%SQL_HOME%\lib\ojdbc8.jar;%CPFILE%                   
SET CPFILE=%SQL_HOME%\lib\orai18n-servlet.jar;%CPFILE%            
SET CPFILE=%SQL_HOME%\lib\xmlparserv2.jar;%CPFILE%
SET CPFILE=%SQL_HOME%\lib\httpmime.jar;%CPFILE%             
SET CPFILE=%SQL_HOME%\lib\oracle.dbtools-common.jar;%CPFILE%    
SET CPFILE=%SQL_HOME%\lib\orai18n-utility.jar;%CPFILE%
SET CPFILE=%SQL_HOME%\lib\jackson-annotations.jar;%CPFILE%  
SET CPFILE=%SQL_HOME%\lib\oracle.dbtools.http.jar;%CPFILE%      
SET CPFILE=%SQL_HOME%\lib\orai18n.jar;%CPFILE%
SET CPFILE=%SQL_HOME%\lib\jackson-core.jar;%CPFILE%         
SET CPFILE=%SQL_HOME%\lib\oracle.dbtools.jdbcrest.jar;%CPFILE%  
SET CPFILE=%SQL_HOME%\lib\orajsoda.jar;%CPFILE%					
<!-- OH dependencies we will want to take onboard -->
SET CPFILE=%SQL_HOME%\modules\oracle.xdk\xmlparserv2.jar;%CPFILE%
SET CPFILE=%SQL_HOME%\rdbms\jlib\xdb6.jar;%CPFILE%
SET CPFILE=%SQL_HOME%\jlib\orai18n-mapping.jar ;%CPFILE%
SET CPFILE=%SQL_HOME%\jlib\orai18n.jar;%CPFILE%
SET CPFILE=%SQL_HOME%\jdbc\lib\ojdbc6.jar;%CPFILE%
SET CPFILE=%SQL_HOME%\jdbc\lib\ojdbc7.jar;%CPFILE%
SET CPFILE=%SQL_HOME%\jdbc\lib\ojdbc8.jar;%CPFILE%
SET CPFILE=%SQL_HOME%\jlib\orai18n-utility.jar;%CPFILE%
<!-- When we ship embedded in SQLDeveloper, heres where we look -->
SET CPFILE=%SQL_HOME%\sqldeveloper\sqlcl\lib\*;%CPFILE%
SET CPFILE=%SQLCL_CLASSPATH%;%CPFILE%

REM Use internal simple X for awt in 
SET STD_ARGS=-Djava.awt.headless=true -Xss10M
if "%ORACLE_HOME%" == "" (
      SET CPFILE=%SQL_HOME%\lib\oracle.sqldeveloper.sqlcl.jar;%CLASSPATH%) ELSE (SET CPFILE=%ORACLE_HOME%\jdbc\lib\ojdbc8.jar;%ORACLE_HOME%\ojdbc8.jar;%SQL_HOME%\lib\oracle.sqldeveloper.sqlcl.jar;%CLASSPATH%
      )
REM Example: SET CUSTOM_JDBC="C:\thirdparty_jdbc_driver.jar"
IF NOT "%CUSTOM_JDBC%" == "" (
      SET CPFILE=%CUSTOM_JDBC%;%CPFILE%
)

REM SET CPFILE=%ORACLE_HOME%\jdbc\lib\ojdbc8.jar;%ORACLE_HOME%\jdbc\lib\ojdbc7.jar;%ORACLE_HOME%\jdbc\lib\ojdbc6.jar;%CPFILE%

REM Have you downloaded a specific jre and dropped it into SQL_HOME as jre
IF NOT EXIST "%SQL_HOME%\jre" GOTO OK
  SET JAVA_HOME=%SQL_HOME%\jre\
  SET PATH=%JAVA_HOME%bin;%PATH%
:OK

IF NOT DEFINED JAVA_HOME (
  java %JAVA_OPTS% %STD_ARGS% %DEBUG% -cp "%CPFILE%" oracle.dbtools.raptor.scriptrunner.cmdline.SqlCli %*
  GOTO EXIT
)
REM if java home is set, then this is where we use it.
"%JAVA_HOME%\bin\java" %JAVA_OPTS% %STD_ARGS% %DEBUG% -cp "%CPFILE%" oracle.dbtools.raptor.scriptrunner.cmdline.SqlCli %*
:EXIT
endlocal
