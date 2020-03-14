In this use case there are 3 data sets : **Final_airlines** , **routes** , **airports_mod**


Dataset description of **Final_airlines** : 
|Field|Description|
|---|---|
|Airline|Unique OpenFlights identifier for this airline. ID|
|Name|Name of the airline|
|Alias|Alias of the airline. For example, All Nippon Airways is commonly known as "ANA".|
|IATA|2-letter IATA code, if available|
|ICAO|3-letter ICAO code, if available|
|Callsign|Airline callsign|
|Country|Country or territory where airline is incorporated|
|Active|"Y" if the airline is or has until recently been operational, "N" if it is defunct|


Random sample data from the dataset **Final_airlines** :
```
[cloudera@quickstart project]$ hdfs dfs -cat /InputFiles/Project/final_airlines.txt | head
1,Private flight,\N,-,N/A,,,Y
2,135 Airways,\N,,GNL,GENERAL,United States,N
3,1Time Airline,\N,1T,RNX,NEXTIME,South Africa,Y
4,2 Sqn No 1 Elementary Flying Training School,\N,,WYT,,United Kingdom,N
5,213 Flight Unit,\N,,TFU,,Russia,N
6,223 Flight Unit State Airline,\N,,CHD,CHKALOVSK-AVIA,Russia,N
7,224th Flight Unit,\N,,TTF,CARGO UNIT,Russia,N
8,247 Jet Ltd,\N,,TWF,CLOUD RUNNER,United Kingdom,N
9,3D Aviation,\N,,SEC,SECUREX,United States,N
10,40-Mile Air,\N,Q5,MLA,MILE-AIR,United States,Y
```

Dataset description of **routes** :
|Fiedl|Description|
|---|---|
|Airline|2-letter (IATA) or 3-letter (ICAO) code of the airline|
|Airline Id|Unique OpenFlights identifier for airline|
|Source Airport|3-letter (IATA) or 4-letter (ICAO) code of the source airport|
|Source Airport Id|Unique OpenFlights identifier for source airport|
|Destination Airport|3-letter (IATA) or 4-letter (ICAO) code of the destination airport|
|Destination Airport Id|Unique OpenFlights identifier for destination airport|
|Codeshare|"Y" if this flight is a codeshare (that is, not operated by Airline, but another carrier), empty otherwise.|
|Stops|Number of stops on this flight ("0" for direct)|
|Equipment|3-letter codes for plane type(s) generally used on this flight, separated by spaces|


Random sample data from the dataset **routes** :
```
[cloudera@quickstart project]$ hdfs dfs -cat /InputFiles/Project/routes.txt | head
2B,410,AER,2965,KZN,2990,,0,CR2
2B,410,ASF,2966,KZN,2990,,0,CR2
2B,410,ASF,2966,MRV,2962,,0,CR2
2B,410,CEK,2968,KZN,2990,,0,CR2
2B,410,CEK,2968,OVB,4078,,0,CR2
2B,410,DME,4029,KZN,2990,,0,CR2
2B,410,DME,4029,NBC,6969,,0,CR2
2B,410,DME,4029,TGK,\N,,0,CR2
2B,410,DME,4029,UUA,6160,,0,CR2
2B,410,EGO,6156,KGD,2952,,0,CR2
```

Dataset description of **airports_mod** :
|Field|Description|
|---|---|
|Airport Id|Unique OpenFlights identifier for this airport|
|Name|Name of airport. May or may not contain the City name|
|City|Main city served by airport. May be spelled differently from Name|
|Country|Country or territory where airport is located|
|IATA/FAA|3-letter FAA code, for airports located in Country "United States of America". 3-letter IATA code, for all other airports. Blank if not assigned.|
|ICAO|4-letter ICAO code. Blank if not assigned|
|Latitude|Decimal degrees, usually to six significant digits. Negative is South, positive is North|
|Longitude|Decimal degrees, usually to six significant digits. Negative is West, positive is East|
|Altitude|In feet|
|Timezone|Hours offset from UTC. Fractional hours are expressed as decimals, eg. India is 5.5|
|DST|Daylight savings time. One of E (Europe), A (US/Canada), S (South America), O (Australia), Z (New Zealand), N (None) or U (Unknown)|


Random sample data from the dataset **airports_mod** :
```
[cloudera@quickstart project]$ hdfs dfs -cat /InputFiles/Project/airports_mod.txt | head
1,Goroka,Goroka,Papua New Guinea,GKA,AYGA,-6.081689,145.391881,5282,10,U,Pacific/Port_Moresby
2,Madang,Madang,Papua New Guinea,MAG,AYMD,-5.207083,145.7887,20,10,U,Pacific/Port_Moresby
3,Mount Hagen,Mount Hagen,Papua New Guinea,HGU,AYMH,-5.826789,144.295861,5388,10,U,Pacific/Port_Moresby
4,Nadzab,Nadzab,Papua New Guinea,LAE,AYNZ,-6.569828,146.726242,239,10,U,Pacific/Port_Moresby
5,Port Moresby Jacksons Intl,Port Moresby,Papua New Guinea,POM,AYPY,-9.443383,147.22005,146,10,U,Pacific/Port_Moresby
6,Wewak Intl,Wewak,Papua New Guinea,WWK,AYWK,-3.583828,143.669186,19,10,U,Pacific/Port_Moresby
7,Narsarsuaq,Narssarssuaq,Greenland,UAK,BGBW,61.160517,-45.425978,112,-3,E,America/Godthab
8,Nuuk,Godthaab,Greenland,GOH,BGGH,64.190922,-51.678064,283,-3,E,America/Godthab
9,Sondre Stromfjord,Sondrestrom,Greenland,SFJ,BGSF,67.016969,-50.689325,165,-3,E,America/Godthab
10,Thule Air Base,Thule,Greenland,THU,BGTL,76.531203,-68.703161,251,-4,E,America/Thule
```
