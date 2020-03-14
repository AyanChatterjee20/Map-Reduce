***Problem Statement*** : Find the list of Airlines having zero stops


**Output** : 
```
[cloudera@quickstart project]$ hdfs dfs -ls /OutputFiles/AirlineZeroStop
Found 2 items
-rw-r--r--   1 cloudera supergroup          0 2020-03-14 17:09 /OutputFiles/AirlineZeroStop/_SUCCESS
-rw-r--r--   1 cloudera supergroup       2857 2020-03-14 17:09 /OutputFiles/AirlineZeroStop/part-r-00000
[cloudera@quickstart project]$ hdfs dfs -cat /OutputFiles/AirlineZeroStop/part-r-00000 | head
Askari Aviation	is a/an airline having zero stop
Abaet	is a/an airline having zero stop
Air Atlanta Icelandic	is a/an airline having zero stop
Air Sahara	is a/an airline having zero stop
Air Arabia	is a/an airline having zero stop
Air Saint Pierre	is a/an airline having zero stop
Aeroflot-Nord	is a/an airline having zero stop
Alitalia	is a/an airline having zero stop
Air Florida	is a/an airline having zero stop
Air Philippines	is a/an airline having zero stop
```
