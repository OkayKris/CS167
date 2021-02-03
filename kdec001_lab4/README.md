


# Lab 4

## Student information
* Full name: Kristian De Castro
* E-mail: kdec001@ucr.edu
* UCR NetID: kdec001
* Student ID: 862025678

## Answers

* (Q1) Do you think it will use your local cluster? Why or why not?

If by local cluster we mean NOT the spark cluster then yes, because we only started spark without reconfiguring any of our code.


* (Q2) Does the application use the cluster that you started? How did you find out?

Yes it does, and thanks to the hint, it shows up on the web interface that a new application has completed


* (Q3) What is the Spark master printed on the standard output on IntelliJ IDEA?

It says "Using Spark master 'local[*]'"


* (Q4) What is the Spark master printed on the standard output on the terminal?

It says "Using Spark's default log4j profile"


* (Q5) For the previous command that prints the number of matching lines, list all the processed input splits.

20/04/27 02:19:22 INFO HadoopRDD: Input split: file:/D:/CS167/kdec001_lab4/nasa_19950801.tsv:1169610+1169610
20/04/27 02:19:22 INFO HadoopRDD: Input split: file:/D:/CS167/kdec001_lab4/nasa_19950801.tsv:0+1169610


* (Q6) For the previous command that counts the lines and prints the output, how many splits were generated?

Two splits


* (Q7) Compare this number to the one you got earlier

Before:
20/04/27 02:19:22 INFO HadoopRDD: Input split: file:/D:/CS167/kdec001_lab4/nasa_19950801.tsv:1169610+1169610
20/04/27 02:19:22 INFO HadoopRDD: Input split: file:/D:/CS167/kdec001_lab4/nasa_19950801.tsv:0+1169610

After:
20/04/27 02:21:58 INFO HadoopRDD: Input split: file:/D:/CS167/kdec001_lab4/nasa_19950801.tsv:1169610+1169610
20/04/27 02:21:58 INFO HadoopRDD: Input split: file:/D:/CS167/kdec001_lab4/nasa_19950801.tsv:0+1169610


* (Q8) Explain why we get these numbers

I assume we get these numbers because the filesize is too big for spark to read in one go


* (Q9) What can you do to the current code to ensure that the file is read only once?

If we use the cache() function in spark, we can store it in memory as we read and when we're done with it we can use unpersist()
