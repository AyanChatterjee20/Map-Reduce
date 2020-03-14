Here I am working with sample dataset of NYSE having **735026** numder of records. 


Details data description field wise : 

|Field|Description|
|---|---|
|Exchange Name|Stock Exchange Name|
|Stock Id|Corresponding Id of particular Stock|
|Date|Date value for each day|
|Open Price|Opening Price for each stock in each day|
|High Price|Maximum Price for each stock in each day|
|Low Price|Minimum Price for each stock in each day|
|Close Price|Closing Price for each stock in each day|
|Volume|Total volume of each stock|
|Adjacent Close Price|Adjacent closing price of each stock|


Sample 10 records from the datasets of NYSE.csv and total number of count of the data : 
```
[cloudera@quickstart src]$ hdfs dfs -cat /InputFiles/NYSE.csv | head
NYSE,AEA,2010-02-08,4.42,4.42,4.21,4.24,205500,4.24
NYSE,AEA,2010-02-05,4.42,4.54,4.22,4.41,194300,4.41
NYSE,AEA,2010-02-04,4.55,4.69,4.39,4.42,233800,4.42
NYSE,AEA,2010-02-03,4.65,4.69,4.50,4.55,182100,4.55
NYSE,AEA,2010-02-02,4.74,5.00,4.62,4.66,222700,4.66
NYSE,AEA,2010-02-01,4.84,4.92,4.68,4.75,194800,4.75
NYSE,AEA,2010-01-29,4.97,5.05,4.76,4.83,222900,4.83
NYSE,AEA,2010-01-28,5.12,5.22,4.81,4.98,283100,4.98
NYSE,AEA,2010-01-27,4.82,5.16,4.79,5.09,243500,5.09
NYSE,AEA,2010-01-26,5.18,5.18,4.81,4.84,554800,4.84
[cloudera@quickstart src]$ hdfs dfs -cat /InputFiles/NYSE.csv | wc -l
735026
```

***Problem Statement 1*** : Find the average stock price with each stock.
**Output** :
```
[cloudera@quickstart src]$ hdfs dfs -cat /OutputFiles/AllTimeAvg/part-r-00000 | head
AA	51.91159715913803
AAI	10.268777015001355
AAN	19.548878615457568
AAP	44.167920310981586
AAR	18.982074468085067
AAV	12.323024390243903
AB	30.259244767970902
ABA	25.813885209713032
ABB	12.447982890589811
ABC	47.1888748995449
```

***Problem Statement 2*** : Find the maximum stock price of each stock with the corresponding date and maximum price.
**Output** :
```

```
