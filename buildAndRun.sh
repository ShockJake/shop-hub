#!/bin/bash

file="./src/main/resources/application.properties"

function prop {
    grep "${1}" ${file} | cut -d'=' -f2
}

sed -i "s/serverPrefix/$(prop "shop_hub.server.prefix")/g" ./0src/main/resources/static/scripts/utils/utils.js

mvn clean package -DskipTests=true spring-boot:repackage
nohup sudo java -jar target/shop-hub-0.0.1-SNAPSHOT.jar > shop_hub.output 2>&1 &
