#!/usr/bin/env sh
mvn clean package
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab9-1.0-SNAPSHOT.jar kc_house_data.csv regression
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab9-1.0-SNAPSHOT.jar sentiment.csv classification
