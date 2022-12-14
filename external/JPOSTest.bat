@echo off

REM Modify the following line to point to the directories containing CSJPOSScanner.dll,CSJPOSScale.dll
set PATH=%PATH%;%ProgramFiles%\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\bin

REM Archive containing the Symbol Serial/USB scanner JPOS service classes

set classpath=%classpath%;%ProgramFiles%\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\External\xerces-2_11_0\xercesImpl.jar
set classpath=%classpath%;%ProgramFiles%\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\External\xerces-2_11_0\xml-apis.jar
set classpath=%classpath%;%ProgramFiles%\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\External\JavaPOS-1.14.0-Dist\javaPOS114.jar

set classpath=%classpath%;%ProgramFiles%\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\bin\JposLogger.jar

set classpath=%classpath%;%ProgramFiles%\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\bin\JposServiceJniScale.jar
set classpath=%classpath%;%ProgramFiles%\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\bin\JposServiceJniScanner.jar

set classpath=%classpath%;%ProgramFiles%\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\bin\JposServiceOnScale.jar
set classpath=%classpath%;%ProgramFiles%\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\bin\JposServiceOnScanner.jar

set classpath=%classpath%;%ProgramFiles%\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\bin\JposServiceScale.jar
set classpath=%classpath%;%ProgramFiles%\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\bin\JposServiceScanner.jar

set classpath=%classpath%;%ProgramFiles%\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\bin\
set classpath=%classpath%;%ProgramFiles%\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\

REM Setting the current directory as the build directory
SET MOT_BUILD_HOME=%~dp0




echo %MOT_BUILD_HOME%
set classpath=%classpath%;%MOT_BUILD_HOME%\JposTest.jar

java JposTest.src.JposSampleApp