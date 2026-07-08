@echo off
setlocal

if "%1"=="" (
    echo Usage:
    echo build-push.bat v10
    exit /b 1
)

set DOCKER_USER=apexhunt
set VERSION=%1

set SERVICES=configserver eurekaserver accounts loans cards message apigatewayserver

for %%S in (%SERVICES%) do (
    echo.
    echo ==============================
    echo Building %%S:%VERSION%
    echo ==============================

    cd %%S

    call mvn clean compile jib:dockerBuild -Djib.to.image=%DOCKER_USER%/%%S:%VERSION%
    if errorlevel 1 exit /b 1

    docker push %DOCKER_USER%/%%S:%VERSION%
    if errorlevel 1 exit /b 1

    cd ..
)

echo.
echo Done!
pause