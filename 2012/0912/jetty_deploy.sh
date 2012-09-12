#!/bin/bash
cp -f /home/sysdev/webapps/unioperation_web_api/web_property_bak/required-conf.properties /home/sysdev/webapps/unioperation_web_api/web-api/src/main/resources
cp -f /home/sysdev/webapps/unioperation_web_api/web_property_bak/api.properties /home/sysdev/webapps/unioperation_web_api/web-api/src/main/resources
cd /home/sysdev/webapps/unioperation_web_api/web-api
mvn clean
nohup mvn jetty:run &
echo '!!!!!!!!!!!!!!!!!!!!!!! unioperation api finish ###############################'

#cp -rf /home/sysdev/webapps/unioperation/web_property_bak/service/* /home/sysdev/webapps/unioperation/web/src/main/resources/service
cp -f /home/sysdev/webapps/unioperation/web_property_bak/required-conf.properties /home/sysdev/webapps/unioperation/web/src/main/resources
cp -f /home/sysdev/webapps/unioperation/web_property_bak/api.properties /home/sysdev/webapps/unioperation/web/src/main/resources
echo '##########################copy resource finishi###############################'
cd /home/sysdev/webapps/unioperation/web
mvn clean
nohup mvn jetty:run &
echo '!!!!!!!!!!!!!!!!!!!!!!! unioperation compile finish ###############################'
tail -f /home/sysdev/webapps/unioperation/web/nohup.out
