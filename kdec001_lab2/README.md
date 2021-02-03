

# Lab 2

## Student information
* Full name: Kristian De Castro
* E-mail: kdec001@ucr.edu
* UCR NetID: kdec001
* Student ID: 862025678

## Answers

* (Q1) Verify the file size and record the running time.

File size: 2,217,980 KB
Running Time: 18.1253374 seconds


* (Q2) Record the running time of the copy command.

real: 10.820 seconds
user: 0.000 seconds
sys: 1.719 seconds


* (Q3) How do the two numbers compare? (The running times of copying the file through your program and the operating system.) Explain in your own words why you see these results.

The runtime on my program is slower than the runtime on my operating system. This may be because my program may not be optimized to run faster than my operating system.



* (Q4) Does the program run after you change the default file system to HDFS? What is the error message, if any, that you get?

My program was able to run through hadoop. I used a small file (26 bytes) that was able to copy in 0.2254616 seconds.


* (Q5) Use your program to test the following cases and record the running time for each case.
  | From       | To           | Runtime (s) |
  | ------------- |:-------------:| -----:|
  | local     | HDFS | 17.0426166 |
  | HDFS      | local      |   16.5077191 |
  | HDFS | HDFS      |    39.2595448 |
