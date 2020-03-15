***Problem Statement*** : List of Airlines operating with code share


**Output** :
```
[cloudera@quickstart project]$ hdfs dfs -ls /OutputFiles/AirlineCodeShare
Found 2 items
-rw-r--r--   1 cloudera supergroup          0 2020-03-15 11:54 /OutputFiles/AirlineCodeShare/_SUCCESS
-rw-r--r--   1 cloudera supergroup       2537 2020-03-15 11:54 /OutputFiles/AirlineCodeShare/part-r-00000
[cloudera@quickstart project]$ hdfs dfs -cat /OutputFiles/AirlineCodeShare/part-r-00000 | head
Abu Dhabi Amiri Flight	Y
Aegean Airlines	Y
Aer Lingus	Y
AeroMéxico	Y
Aeroflot Russian Airlines	Y
Aeroflot-Nord	Y
Aerolane	Y
Aerolineas Argentinas	Y
Air Astana	Y
Air Baltic	Y
[cloudera@quickstart project]$ 
```
