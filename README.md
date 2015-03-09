Yahoo! Cloud System Benchmark (YCSB)
====================================

Fork of YCSB used for Progger database benchmarking. Created during the University of Waikato's Summer Research Scholarship programme 2014-15.

Changes
-----

1. New Workload (ProggerWorkload) - generates records and fields according to a distribution provided by an analysis file. Set workload=com.yahoo.ycsb.workloads.ProggerWorkload as a property.
  New ByteIterators (DateByteIterator, IntByteIterator and ProggerIterator were developed as part of this. All byte iterators are also moved to a new directory.
  
2. New Properties  
  _keytypelong_ (default=false) - if true, primary keys will be integer (long) values, instead of strings (e.g. just 22 instead of "user22")  
  _proggersummary_ (default=syscall_summary.txt) - file name of the progger analysis file used for the Progger workload. NOTE: This should be located outside in the home directory, at the same layer as the root YCSB folder.  

Previous repos
-----
https://github.com/brianfrankcooper/YCSB - Original YCSB repo  
  
https://github.com/jbellis/YCSB - Much more recent adaption with numerous changes  
(This is based off another repository created by Thumbtack Technology during their NoSQL Benchmarking white paper (http://www.aerospike.com/wp-content/uploads/2013/01/Ultra-High-Performance-NoSQL-Benchmarking.pdf). Pages 41-43 highlight all the changes they made to the original YCSB framework.

See https://github.com/brianfrankcooper/YCSB/wiki/ for documentation on how to run YCSB, along with the original properties.
