mvn clean install
scp ./zt-report-service/target/zt-report-service.jar root@172.16.20.227:/data/service/
ssh root@172.16.20.227 systemctl restart zt-report-service