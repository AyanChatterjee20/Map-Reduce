***Problem Statement*** : Which country (or) territory having highest Airports

**Output** : 
```
[cloudera@quickstart project]$ hdfs dfs -ls /OutputFiles/MaxAirportsCountry
Found 2 items
-rw-r--r--   1 cloudera supergroup          0 2020-03-14 17:29 /OutputFiles/MaxAirportsCountry/_SUCCESS
-rw-r--r--   1 cloudera supergroup         19 2020-03-14 17:29 /OutputFiles/MaxAirportsCountry/part-r-00000
[cloudera@quickstart project]$ hdfs dfs -cat /OutputFiles/MaxAirportsCountry/part-r-00000
United States	1697
```
