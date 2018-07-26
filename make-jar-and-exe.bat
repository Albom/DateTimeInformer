call mvn clean package
del target\DateTimeInformer-0.2.jar
ren target\DateTimeInformer-0.2-shaded.jar DateTimeInformer-0.2.jar
pause
