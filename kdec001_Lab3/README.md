

# Lab 3
 
## Student information
* Full name: Kristian De Castro
* E-mail: kdec001@ucr.edu
* UCR NetID: kdec001
* Student ID: 862025678

## Answers

* (Q1) What do you think the line job.setJarByClass(Filter.class); does?

I think this command creates a job for a filter


* (Q2) What is the effect of the line job.setNumReduceTasks(0);?

I think this lets us do map-only executions and not reduce executions


* (Q3) Where does the main function run? (Driver node, Master node, or a slave node).

The main function runs in the driver node



* (Q4) How many lines do you see in the output?

I see 27972 lines


* (Q5) How many files are produced in the output?

  Only 1 file is produced in this output.


* (Q6) Explain this number based on the input file size and default block size.

There is only one file because the sample file we are using is only 2.2 MB. The default block size is 128MB so there is no need to make multiple output files.


* (Q7) How many files are produced in the output? (HDFS)

  Same as Q5, only 1 file is produced in this output.


* (Q8) Explain this number based on the input file size and default block size. (HDFS)

Again, like Q6, there is only one file because the sample file we are using is only 2.2 MB. The default block size is 128MB so there is no need to make multiple output files.


* (Q9) How many files are produced in the output directory and how many lines are there in each file?

It seems that two files are produced, but only 4 lines total. 4 in the first file, 0 in the second.


* (Q10) Explain these numbers based on the number of reducers and number of response codes in the input file.

I think the numbers are low because we set the intermediate output to a set response code and bytes, which then goes to the reduce function to find out how many values are the same for that specific response.


* (Q11) How many files are produced in the output directory and how many lines are there in each file?

There are two files created, 5 lines in one, 2 lines in the other.


* (Q12) Explain these numbers based on the number of reducers and number of response codes in the input file.

Just like before, we set an intermediate output and the reducer finds the amount of response types that have the same value.


* (Q13) How many files are produced in the output directory and how many lines are there in each file?

There are two files and the first one has 1533533 lines, and the other has 168001 lines.


* (Q14) Explain these numbers based on the number of reducers and number of response codes in the input file.

For the filter, our response code was 200, so we searched for all types that had code 200 and appended it to these files.
