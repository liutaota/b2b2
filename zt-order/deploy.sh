mvn clean install
scp ./zt-order-service/target/zt-order-service.jar root@172.16.20.227:/data/service/
#ssh root@172.16.20.227 systemctl restart zt-order-service