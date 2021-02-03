# Lab 5

## Student information
* Full name: Kristian De Castro
* E-mail: kdec001@ucr.edu
* UCR NetID: kdec001
* Student ID: 862025678

## Answers

*  (Q) What are these two arguments?
The command arguments are the commands we are going to implement: count-all, code-filter, etc..
The input file are the two sample files we downloaded that we will run the commands on.


package edu.ucr.cs.cs167.kdec001

/**
 * @author ${user.name}
 */
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession


object App {

  def main(args : Array[String]) {
    val conf = new SparkConf
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")
    println(s"Using Spark master '${conf.get("spark.master")}'")

    val spark = SparkSession
      .builder()
      .appName("CS167 Lab6")
      .config(conf)
      .getOrCreate()

    try {
      import spark.implicits._

      val command: String = args(0)
      val inputfile: String = args(1)

      val input = spark.read.format("csv")
        .option("sep", "\t")
        .option("inferSchema", "true")
        .option("header", "true")
        .load(inputfile)

      val t1 = System.nanoTime
      command match {
        case "count-all" =>
          val count = input.count();
          println(s"Total count for file '$inputfile' is $count")
        // TODO count total number of records in the file
        case "code-filter" =>
          val responseCode: String = args(2)
          val filter = input.filter($"response" === responseCode);
          val totalFiltered = filter.count();
          println(s"Total count for file '$inputfile' with response code ${responseCode} is ${totalFiltered}")
        // TODO Filter the file by response code, args(2), and print the total number of matching lines
        case "time-filter" =>
          val start = args(2);
          val end = args(3);
          val elapsed = input.filter($"time".between(start,end)).count();

          println(s"Total count for file ${inputfile} in time range [${start},${end}] is ${elapsed}")

        // TODO Filter by time range [from = args(2), to = args(3)], and print the total number of matching lines
        case "count-by-code" =>
          val totalCount = input.groupBy($"response").count()
          println(s"Number of lines per code for the file ${inputfile}")
          totalCount.show();
        // TODO Group the lines by response code and count the number of records per group
        case "sum-bytes-by-code" =>
          val totalSum = input.groupBy($"response").sum("bytes")
          println(s"Number of lines per code for the file ${inputfile}")
          totalSum.show();
        // TODO Group the lines by response code and sum the total bytes per group
        case "avg-bytes-by-code" =>
          val avgBytes = input.groupBy($"response").avg("bytes")
          println(s"Number of lines per code for the file ${inputfile}")
          avgBytes.show();
        // TODO Group the liens by response code and calculate the average bytes per group
        case "top-host" =>
          val topHost = input.groupBy($"host").count().orderBy($"count".desc)
          val hostName = topHost.first()
          println(s"Top host in the file ${inputfile} by number of entries")
          println(s"Host: ${hostName.getAs("host")}")
          println(s"Number of entries: ${hostName.getAs("count")}")

        // TODO print the host the largest number of lines and print the number of lines
        case "comparison" =>
          val time = args(2)
          val before = input.filter($"time" < time).groupBy($"response").count().withColumnRenamed("count","count_before")
          val after = input.filter($"time" >= time).groupBy($"response").count().withColumnRenamed("count","count_after")
          val combined = before.join(after, "response")
          println(s"Comparison of the number of lines per code before and after ${time} on file ${inputfile}")
          combined.show()
        // TODO Given a specific time, calculate the number of lines per response code for the
        // entries that happened before that time, and once more for the lines that happened at or after
        // that time. Print them side-by-side in a tabular form.
      }
      val t2 = System.nanoTime
      println(s"Command '${command}' on file '${inputfile}' finished in ${(t2-t1)*1E-9} seconds")

    } finally {
      spark.stop
    }
  }
}
