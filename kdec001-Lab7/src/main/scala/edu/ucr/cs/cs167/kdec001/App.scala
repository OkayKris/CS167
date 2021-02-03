package edu.ucr.cs.cs167.kdec001

/**
 * @author ${user.name}
 */
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.SparkConf

object App {

  def main(args : Array[String]) {
    val operation: String = args(0)
    val inputfile: String = args(1)

    val conf = new SparkConf
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")
    //println(s"Using Spark master '${conf.get("spark.master")}'")

    val spark = SparkSession
      .builder()
      .appName("CS167 Lab7")
      .config(conf)
      .getOrCreate()

    try {
      import spark.implicits._
      // TODO Load input

      val input : DataFrame = if(inputfile.endsWith(".csv"))
        spark.read.format("csv")
          .option("sep", ",")
          .option("inferSchema", "true")
          .option("header", "true")
          .load(inputfile)
          .withColumnRenamed("Case Number", "Case_Number")
          .withColumnRenamed("Primary Type", "Primary_Type")
          .withColumnRenamed("Location Description", "Location_Description")
          .withColumnRenamed("Community Area", "Community_Area")
          .withColumnRenamed("FBI Code", "FBI_Code")
          .withColumnRenamed("X Coordinate", "X_Coordinate")
          .withColumnRenamed("Y Coordinate", "Y_Coordinate")
          .withColumnRenamed("Updated On", "Updated_On")

      else
      spark.read.parquet(inputfile)

      input.show()
      input.printSchema()

      val t1 = System.nanoTime
      operation match {
        case "write-parquet" =>
        // TODO Write the input dataset to a parquet file. The file name is passed in args(2)
          val outputfile = args(2)
          input.write.mode("overwrite").parquet(outputfile)

        case "write-parquet-partitioned" =>
        // TODO Write the input dataset to a partitioned parquet file by District. The file name is passed in args(2)
          val outputfile = args(2)
          input.write.mode("overwrite").partitionBy("District").parquet(outputfile)
        case "top-crime-types" =>
        // TODO Print out the top five crime types by count "Primary_Type"
          input.createOrReplaceTempView("crimes")
          spark.sql("select Primary_Type, count(*) as count from crimes group by Primary_Type order by count desc limit 5").show()
        case "find" =>
        // TODO Find a record by Case_Number in args(2)
          val casenumber = args(2)
          input.createOrReplaceTempView("crimes")
          spark.sql(s"select * from crimes where Case_Number = '$casenumber'").show()
        case "stats" =>
        // TODO Compute the number of arrests, domestic crimes, and average beat per district.
        // Save the output to a new parquet file named "stats.parquet"
          input.createOrReplaceTempView("crimes")
          spark.sql("select District, sum(int(Arrest)) as num_arrests, sum(int(Domestic)) as num_domestic, avg(Beat) as avg_beat from crimes group by District").write.mode("overwrite").parquet("stats.parquet")
        case "stats-district"  =>
        // TODO Compute number of arrests, domestic crimes, and average beat for one district (args(2))
        // Write the result to the standard output
          val district = args(2)
          input.createOrReplaceTempView("crimes")
          spark.sql(s"select District, sum(int(Arrest)) as num_arrests, sum(int(Domestic)) as num_domestic, avg(Beat) as avg_beat from crimes where District = '$district' group by District").show()
      }

      val t2 = System.nanoTime
      println(s"Operation $operation on file '$inputfile' finished in ${(t2-t1)*1E-9} seconds")
    } finally {
      spark.stop
    }
  }
}
