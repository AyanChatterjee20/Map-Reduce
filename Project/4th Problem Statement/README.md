***Problem Statement*** : Find the list of Active Airlines in United state


**Output** :
```
[cloudera@quickstart project]$ hdfs dfs -ls /OutputFiles/ActiveAirlinesUnitedState
Found 2 items
-rw-r--r--   1 cloudera supergroup          0 2020-03-14 17:39 /OutputFiles/ActiveAirlinesUnitedState/_SUCCESS
-rw-r--r--   1 cloudera supergroup       4422 2020-03-14 17:39 /OutputFiles/ActiveAirlinesUnitedState/part-r-00000
[cloudera@quickstart project]$ hdfs dfs -cat /OutputFiles/ActiveAirlinesUnitedState/part-r-00000 | head
United States	All America
United States	Spike Airlines
United States	Rainbow Air US
United States	Rainbow Air Polynesia
United States	Rainbow Air (RAI)
United States	Envoy Air
United States	XAIR USA
United States	Comfort Express Virtual Charters
United States	Comfort Express Virtual Charters Albany
United States	Eastern Atlantic Virtual Airlines
```
