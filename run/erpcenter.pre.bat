cd /d %~dp0..
call mvn package -DskipTests

call autoconfig %~dp0..\target\erpcenter-web.war -u %~dp0erpcenter.pre.properties
@pause