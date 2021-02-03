#!/usr/bin/env sh
mvn clean package
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab7-1.0-SNAPSHOT.jar write-parquet Crimes_-_2001_to_present.csv non_partitioned.parquet
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab7-1.0-SNAPSHOT.jar write-parquet-partitioned Crimes_-_2001_to_present.csv partitioned.parquet
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab7-1.0-SNAPSHOT.jar top-crime-types Crimes_-_2001_to_present.csv
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab7-1.0-SNAPSHOT.jar top-crime-types non_partitioned.parquet
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab7-1.0-SNAPSHOT.jar top-crime-types partitioned.parquet
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab7-1.0-SNAPSHOT.jar find Crimes_-_2001_to_present.csv HY413923
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab7-1.0-SNAPSHOT.jar find non_partitioned.parquet HY413923
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab7-1.0-SNAPSHOT.jar find partitioned.parquet HY413923
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab7-1.0-SNAPSHOT.jar stats Crimes_-_2001_to_present.csv
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab7-1.0-SNAPSHOT.jar stats non_partitioned.parquet
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab7-1.0-SNAPSHOT.jar stats partitioned.parquet
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab7-1.0-SNAPSHOT.jar stats-district Crimes_-_2001_to_present.csv 8
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab7-1.0-SNAPSHOT.jar stats-district non_partitioned.parquet 8
spark-submit --class edu.ucr.cs.cs167.kdec001.App target/kdec001_lab7-1.0-SNAPSHOT.jar stats-district partitioned.parquet 8
