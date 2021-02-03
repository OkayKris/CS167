package edu.ucr.cs.cs167.kdec001

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object App {


  def main(args : Array[String]) {
    val command: String = args(0)
    val inputfile: String = args(1)

    val conf = new SparkConf
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")
    println(s"Using Spark master '${conf.get("spark.master")}'")
    conf.setAppName("lab5")
    val sparkContext = new SparkContext(conf)
    try {
      val inputRDD: RDD[String] = sparkContext.textFile(inputfile)
      // TODO Parse the input file using the tab separator and skip the first line
      val filterRDD = inputRDD.filter(line=>(!line.startsWith("host\tlogname"))).map(line=>line.split("\t"))
      val ResponseCode: Int = 5
      val Timestamp: Int = 2
      val Bytes: Int = 6
      val Host: Int = 0
      val t1 = System.nanoTime
      command match {
        case "count-all" =>
        println(s"Total count for file '${inputfile}' is ${filterRDD.count()}")
        // TODO count total number of records in the file
        case "code-filter" =>
          val inputResponse = args(2)
          val filteredCode = filterRDD.filter(line=>line(ResponseCode).equals(inputResponse))
          val count = filteredCode.count();
          println(s"Total count for file '${inputfile}' with response code ${inputResponse} is ${count}")
        // TODO Filter the file by response code, args(2), and print the total number of matching lines
        case "time-filter" =>
          val start: Long = args(2).toLong
          val end: Long = args(3).toLong
          val range = filterRDD.filter(line=> line(Timestamp).toLong >= start && line(Timestamp).toLong <= end).count()
          println(s"Total count for file '${inputfile}' in time range [$start, $end]' is $range")
        // TODO Filter by time range [from = args(2), to = args(3)], and print the total number of matching lines
        case "count-by-code" =>
          val cbc: collection.Map[String, Long] = filterRDD.map(line=>(line(ResponseCode), 1)).countByKey()
          println(s"Number of lines per code for the file '${inputfile}'")
          println(s"Code, Count")
          cbc.foreach(codeCount => println(s"${codeCount._1},${codeCount._2}"))
        // TODO Group the lines by response code and count the number of records per group
        case "sum-bytes-by-code" =>
          val sumbytes = filterRDD.map(line=>(line(ResponseCode), line(Bytes).toLong)).reduceByKey(_+_)
          println(s"Total bytes per code for the file $inputfile")
          println(s"Code, Sum(Bytes)")
          sumbytes.collect().foreach(bytes => println(s"${bytes._1},${bytes._2}"))
        // TODO Group the lines by response code and sum the total bytes per group
        case "avg-bytes-by-code" =>
          val cbc: collection.Map[String, Long] = filterRDD.map(line=>(line(ResponseCode), 1)).countByKey()
          val sumbytes = filterRDD.map(line=>(line(ResponseCode), line(Bytes).toLong)).reduceByKey(_+_)
          //val ave = sumbytes.collect().count() / cbc.count()
          println(s"Total average bytes per code for the file $inputfile")
          println(s"Code, Avg(Bytes)")
        // TODO Group the liens by response code and calculate the average bytes per group
        case "top-host" =>
          val tophost = filterRDD.map(line=>(line(Host),1)).reduceByKey(_+_).sortBy(_._2, false).collect()

          println(s"Top host in the file $inputfile by number of entries")
          println(s"Host: ${tophost.head._1}")
          println(s"Number of entries: ${tophost.head._2}")
        // TODO print the host the largest number of lines and print the number of lines
        case "comparison" =>
          val time: Long = args(2).toLong
          val before = filterRDD.filter(line=>line(Timestamp).toLong <= time)
          val after = filterRDD.filter(line=>line(Timestamp).toLong >= time)
          val cbc: collection.Map[String, Long] = before.map(line=>(line(ResponseCode), 1)).countByKey()
          val cbc2: collection.Map[String, Long] = after.map(line=>(line(ResponseCode), 1)).countByKey()
          val combo = cbc++cbc2
          var i: Int = 0
          println(s"Comparison of the number of lines per code before and after $time on file $inputfile")
          println(s"Code,Count before,Count after")
          cbc.foreach(pair=>println(s"${pair._1},${pair._2},${cbc2(pair._1)}"))

        // TODO Given a specific time, calculate the number of lines per response code for the
        // entries that happened before that time, and once more for the lines that happened at or after
        // that time. Print them side-by-side in a tabular form.
      }
      val t2 = System.nanoTime
      println(s"Command '${command}' on file '${inputfile}' finished in ${(t2-t1)*1E-9} seconds")
    } finally {
      sparkContext.stop
    }
  }
}
