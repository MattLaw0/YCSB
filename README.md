Yahoo! Cloud System Benchmark (YCSB)
====================================

Fork of YCSB used for Progger database benchmarking. Created during the University of Waikato's Summer Research Scholarship programme 2014-15.

Changes
-----

1. New Workload (ProggerWorkload)  
  Generates records and fields according to a distribution provided by an analysis file. Set workload=com.yahoo.ycsb.workloads.ProggerWorkload as a property.  
  New ByteIterators (DateByteIterator, IntByteIterator and ProggerIterator) were developed as part of this. All byte iterators are also moved to a new directory.
  
2. New Properties  
  _keytypelong_ (default=false) - if true, primary keys will be integer (long) values, instead of strings (e.g. just 22 instead of "user22")  
  _proggersummary_ (default=syscall_summary.txt) - file name of the progger analysis file used for the Progger workload. NOTE: This should be located outside in the home directory, at the same layer as the root YCSB folder.  
  
3. Driver modifications  
  The CassandraCQLClient and JdbcDBClient were modified slightly in order to cope with the changes made to the Progger Workload. Essentially the insert statements needed to be predefined with a fixed number of fields (mainly due to having to define a schema beforehand) - the Progger workload generates a varied number. Therefore, as a simple workaround, the predefined statements were created with the maximum number of fields of any record (14), and if there were less than that many fields for a particular record, nulls were inserted into the remainder of the field. This is not ideal, and more sophisticated record generation should be considered in future that allows for varying numbers of fields and types.

Previous repos
-----
https://github.com/brianfrankcooper/YCSB - Original YCSB repo  
  
https://github.com/jbellis/YCSB - Much more recent adaption with numerous changes  
(This is based off another repository created by Thumbtack Technology during their NoSQL Benchmarking white paper (http://www.aerospike.com/wp-content/uploads/2013/01/Ultra-High-Performance-NoSQL-Benchmarking.pdf). Pages 41-43 highlight all the changes they made to the original YCSB framework.

See https://github.com/brianfrankcooper/YCSB/wiki/ for documentation on how to run YCSB, along with the original properties.
