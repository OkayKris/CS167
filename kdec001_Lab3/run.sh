#!/bin/bash
mvn clean package
#yarn jar target/kdec001_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.kdec001.Filter nasa_19950801.tsv filter_output.tsv 200
yarn jar target/kdec001_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.kdec001.Filter nasa_19950630.22-19950728.12.tsv filter_output.tsv 200
#yarn jar target/kdec001_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.kdec001.Aggregation nasa_19950801.tsv filter_output.tsv
yarn jar target/kdec001_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.kdec001.Aggregation nasa_19950630.22-19950728.12.tsv aggregation_output.tsv
