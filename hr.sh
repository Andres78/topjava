#!/bin/sh
mvn -B -s settings.xml -DskipTests=true clean package
java -Dspring.profiles.active="datajpa,heroku" -DDATABASE_URL="postgres://ktjncjpyhkmprj:Ck-i3gfvgVRQ8RyIikUXA1PFjH@localhost:5432/topjava" -jar target/dependency/webapp-runner.jar target/*.war