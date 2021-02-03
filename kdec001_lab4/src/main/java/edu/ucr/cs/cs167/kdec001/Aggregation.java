package edu.ucr.cs.cs167.kdec001;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Aggregation {
    public static void main( String[] args ) {
        final String inputfile = args[0];
        final String outputFile = args[1];
        final String desiredCode = args[2];
        SparkConf conf = new SparkConf();
        if (!conf.contains("spark.master"))
            conf.setMaster("local[*]");
        System.out.printf("Using Spark master '%s'\n", conf.get("spark.master"));
        conf.setAppName("lab4");
        JavaSparkContext spark = new JavaSparkContext(conf);
        try {
            JavaRDD<String> logFile = spark.textFile(inputfile);
            //System.out.printf("Number of lines in the log file %d\n", logFile.count());
            //JavaRDD<String> matchingLines = logFile.filter(line -> line.split("\t")[5].equals(desiredCode));
            //System.out.printf("The file '%s' contains %d lines with response code %s\n", inputfile, matchingLines.count(), desiredCode);

            JavaPairRDD<String, Integer> rdd1 = logFile.mapToPair(line -> new Tuple2<String, Integer>(line.split("\t")[5],1));
            Map<String, Long> map1 = rdd1.countByKey();

            BufferedWriter toFile = new BufferedWriter(new FileWriter(outputFile));

            for(Map.Entry<String, Long> start : map1.entrySet()) {
                String record = "Code '" + start.getKey() + " : number of entries " + start.getValue() + "\n";
                System.out.println(record);
                toFile.write(record);
            }

            toFile.close();

            //matchingLines.saveAsTextFile(outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            spark.close();
        }
    }
}
