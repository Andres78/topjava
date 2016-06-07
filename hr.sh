#!/bin/sh
mvn -B -s settings.xml -DskipTests=true clean package
java -Dspring.profiles.active="datajpa,heroku" -DDATABASE_URL="postgres://posgres:postgres@localhost:5432/topjava" -jar target/dependency/webapp-runner.jar target/*.war
