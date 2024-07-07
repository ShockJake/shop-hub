#!/bin/bash

file="./src/main/resources/application.properties"

function prop {
	grep "${1}" ${file} | cut -d'=' -f2
}

server_prefix=$(prop "shop_hub.server.prefix")
server_prefix=${server_prefix::-1}
echo -e "\n - Setting server prefix to: ${server_prefix}"
sed -i "s|serverPrefix|${server_prefix}|" ./src/main/resources/static/scripts/utils/utils.js

echo -e "\n - Building Application"
mvn clean package -DskipTests=true spring-boot:repackage

echo -e "\n - Running Application"
nohup sudo java -jar target/shop-hub-0.0.1-SNAPSHOT.jar > shop_hub.output 2>&1 &
pid=$!
echo -e "\n - Application PID: ${pid}, it is saved in 'shop_hub.pid' file"
echo "${pid}" > ./shop_hub.pid
