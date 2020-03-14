***Problem Statement*** : Find list of Airports operating in the Country India

**Output** : 
```
[cloudera@quickstart project]$ hdfs dfs -ls /OutputFiles/IndiaAirports
Found 2 items
-rw-r--r--   1 cloudera supergroup          0 2020-03-14 17:21 /OutputFiles/IndiaAirports/_SUCCESS
-rw-r--r--   1 cloudera supergroup       2365 2020-03-14 17:21 /OutputFiles/IndiaAirports/part-r-00000
[cloudera@quickstart project]$ hdfs dfs -cat /OutputFiles/IndiaAirports/part-r-00000 | head
India	Helipad of Viraj Group
India	Cochin Port
India	Mormugao-Goa Port
India	Yelahanka AFB
India	Havelock Island Seaport
India	Gangtok Helipad
India	Agra Cantonment Railway Station
India	New Delhi Train Station
India	Nagaur Airport
India	Mysore Airport
```
