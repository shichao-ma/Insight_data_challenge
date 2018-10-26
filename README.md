# Insight Data Challenge

This project is design for accomplishing the [Insight Data Challenge](https://github.com/InsightDataScience/h1b_statistics), in which I am given a dataset that contains some information on H1B applications and need to write a program that does some data aggregation without using canned solutuions.

# How to Run

You can exucute it through **run.sh**:
```
$ run.sh
```
It will compile the source code and do the data aggregation. Or, alternatively, you may use the executable jar file ([H1bDataManipulation.jar](https://github.com/skuimsc/Insight_data_challenge/blob/master/H1bDataManipulation.jar "H1bDataManipulation.jar")) that I provided.
## Requirements
Java 8 and up.

# Features

The program does three jobs:

 1. Read and parse a `.csv` file. As I am not allowed to use canned solutions (e.g., [OpenCSV](http://opencsv.sourceforge.net)),  I write my own CSV reader and writer.
 2. Count the number of entries based on a given counting field. 
 3. Sort the number of entries and write the largest ten 

<!--stackedit_data:
eyJoaXN0b3J5IjpbOTA1OTcwMzA4LDMyMjE4MDg4LC04Mzg0MT
g0MzEsNDU0NjgzMDMsLTEyNDAwNTQzODAsLTE0OTA5ODM1NzEs
MTIwMTgxOTI4LDU4NDAwNjEzOCwtMTU1OTMyMTc1NywtMTc0Mz
Q2NDQ2OV19
-->