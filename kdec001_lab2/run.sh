#!/bin/bash
mvn clean package
hadoop jar target/kdec001_lab2-1.0-SNAPSHOT.jar file:///$pwd/AREAWATER.csv hdfs:///AREAWATER.csv
hadoop jar target/kdec001_lab2-1.0-SNAPSHOT.jar hdfs:///AREAWATER.csv hdfs:///AREAWATERcopy.csv
hadoop jar target/kdec001_lab2-1.0-SNAPSHOT.jar hdfs:///AREAWATER.csv file:///$pwd/AREAWATER.csv
