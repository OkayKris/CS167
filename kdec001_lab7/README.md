# Lab 7

## Student information
* Full name: Kristian De Castro
* E-mail: kdec001@ucr.edu
* UCR NetID: kdec001
* Student ID: 862025678

## Answers

* (Q1) What are the top five crime types?

Theft, battery, criminal damage, narcotics, assault.


* (Q2) Compare the sizes of the CSV file and the resulting Parquet file? What do you notice? Explain.

The CSV file is 1.6 GB while the parquet file is a mere 435 MB. It seems like it split and reduced into multiple files.


* (Q3) How many times do you see the schema the output? How does this relate to the number of files in the output directory? What do you make of that?

About 16 times. It created the parquet type tree so now the data are stored in columns.


* (Q4) How does the output look like? How many files were generated?

The files were separated into 31 district folders with a default partition folders


* (Q5) Explain an efficient way to run this query on a column store.

I assume this means how to search, so what we would do is find the case_number column and return its attached data.


# Running time (seconds)

|    |Time on CSV |Time on non-partitioned Parquet | Time on partitioned Parquet
|----------|------------|-----------| ----------|
|top-crime-types|4.2201428000000005 seconds|2.4887946000000003 seconds | 3.3154531 seconds
|find          |8.177746800000001 seconds |2.0383396 seconds |2.2675941 seconds
|stats          |4.804296900000001 seconds|2.9086889 seconds|3.2591652 seconds
|stats-district |3.7701735000000003 seconds|1.8584852 seconds|1.7416669 seconds
