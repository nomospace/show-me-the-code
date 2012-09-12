ps -ef|grep jetty|grep root|awk '{print $2}'|xargs kill -9
