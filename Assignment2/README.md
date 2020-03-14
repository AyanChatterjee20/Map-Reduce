Here I am working with a text file describes about Mahatma Gandhi which saved as **/InputFiles/word.txt** in HDFS.
We  have considered '\n' (new line) as record delimeter and ' ' (space) as field delimeter . 

***Problem Statement*** : Count the number of words in the corresponding document.
**Output** : 
```
[cloudera@quickstart src]$ hdfs dfs -cat /OutputFiles/WordCount/part-r-00000 | head -30
"Great	1
"I	1
(a	1
(cotton).	1
(pg	1
112).	1
1869	1
1888,	1
1893,	1
1947	1
1948.	1
2,	1
30,	1
78	1
Africa	1
Africans.	1
As	1
Back	1
Because	1
Bravery	1
British	4
British,	1
Dandi	3
Even	1
Experiments	1
For	1
Gandhi	21
Gandhi's	1
Gandhi,	4
Gandhi.	3
```


***Problem Statement*** : List the words on the basis of there size . 
**Output** :
```
[cloudera@quickstart src]$ hdfs dfs -cat /OutputFiles/AlphabetCount/part-r-00000 | head -20
1	The same size list of word is:  a a a a a a a a a a a a a a a I a a a a
2	The same size list of word is:  In he of He (a to in he he an no be to "I In an be to to he up no he He of by My of to he he in he to it he 2, he of of He on He go he is in to to in is to to to of of he he to in He He in in in as by be of He an he by to to he in he in is he of of to He he by up he to in is to on of to to to in am of as to as He by as of in to to of by He up of by by in an by He of on of by he in 78 He to he is of of an at in As is as in he if in do to he of if He in to In he an is he an he to he he as be to
3	The same size list of word is:  the was for not got now the the and and can and and own his did age his for the the can one was was the the the had the and Tax and the was and did him the was and the him did his are the but The not for for was was and all the the (pg Law the The all the the and the the was the who get for his was did and the and did set out was and the man the his was and the not the and was our the did not his his was and and act led The For the and the was own war and his the end the way for the was and the for led was and who was one own his for but own his was the 30, low The and was was was not and son the was the can the was and his the was the up. for The not The are man did and the who fit had was how for and was the and big way The One any did not did and the one say not did lot but way his
4	The same size list of word is:  that law. went work that hero that main hero That most what hero true were that only that till many war, love with done that what also This love type with Many also came very also pay. that Salt that with than more with many too. made Salt This work was. show that many gave that make that that they will have they only make hero made what like that just him, want want that This than less with than more man: many even with look book with also days many then born made were that wore only poor like 1869 Even that only with that most made that away many took This 1947 This had. deed Last near were they what have made This were they that same them were what that made with less word just most just kind were that used Back went many wife This well make poor
5	The same size list of word is:  days, word. Later first ended class there them. found using three India truth India brave Hindu broke poor, known first brave hero, Dandi Dandi South doing India caste those which place doing using hero. would Dandi would 1888, khadi truth South 1948. said, years their March hero. died, began wrote hero. where Story India among brave start doing claim learn from. 112). quote tells known never 1893, three Soul" thing peace known march fight which life, their times doing hero, life. India right issue thing peace salt. there could love. being
6	The same size list of word is:  Hindus second passed "Great Gandhi Gandhi skills degree Gandhi anyone become wanted Gandhi Gandhi things March. things leader Indian Gandhi Gandhi Gandhi heroic fought Gandhi Indian least, years. Gandhi Gandhi March, enough reason Gandhi pursue Hindus people became Gandhi Gandhi define Gandhi people beaten strong Gandhi person middle peace. strong didn’t really faith, people heroic Gandhi others Africa showed right. things peace. people became India. eating Gandhi growth truth. truth, person fought Truth. though heroic fought though hatred person served became Hindus simple anyone wanted simple living truth, Gandhi Gandhi Gandhi heroic
7	The same size list of word is:  Gandhi. bravery through several India’s bravery because reason, through results bravery Lastly, because towards violent execute people, British country Gandhi, support Indians British India's British towards example Gandhi. quality another Bravery result, others. believe others. average proving Gandhi, readers average average average claimed things, titled, Because because without clothes dressed needed. quality because Gandhi. realize British realize people. another system. against against poverty outside village clothes village village towards behind, leaving London, Rajkot, brought Mahatma January October thought brought Gandhi, Gandhi,
8	The same size list of word is:  ability" Indians, everyone economy. Mohandas creating believed employed Mohandas movement bravery. homespun village. believed Gandhi’s village. studies. Indians. examples ordinary describe educated Mahatma, Movement all-time movement Mahatma. movement homespun governed British, believed believed Homespun Gandhi’s believed Gujarat. believed believer favorite hobbies. lawyer), behavior Gandhi's himself. homespun believed promised
9	The same size list of word is:  qualities something Moreover, spiritual believing Moreover, prejudice countries complain. village’s millions. homespun, Africans. addition, (cotton). injustice barrister countless clothing. qualities describes possessed political showcased beginning sacrifice obstacles inspiring
10	The same size list of word is:  qualities. leadership Karamchand diminished simplicity Therefore, leadership Simplicity Kathiawar, Porbandar, arrogance. everything immigrants especially worshiping simplicity opposition imprisoned diminished definitely principles qualities, protesting eventually simplicity Leadership vegetarian
11	The same size list of word is:  necessities outstanding drastically Introducing Leadership, conclusion, astonishing encouraging leadership, non-violent resistance. nonviolence Experiments fruitarian; outstanding
12	The same size list of word is:  discriminate nonviolence. accomplished independence Independence untouchables independent. demonstrated
13	The same size list of word is:  independence. demonstrated. untouchables, untouchables. independence. untouchables. untouchables.
14	The same size list of word is:  discrimination
```
