## 6650 Assignment 4 ##

URL: <a/>https://github.com/tjuqxb/6650-assignment-4</a>  

### Database Design ###
Redis is a Key-Value database and does not support complex join queries. This design uses key names as descriptions of query conditions and values as query results.    
It uses Redis Sets to contain non-duplicate records (e.g., the days one user skied in one season) and Redis Strings to record numeric values (e.g., vertical totals for each ski day for a specific user).It uses Reids Hashes to manage structured data with two layers(e.g., management of season and day).     

GET/resorts/{resortID}/seasons/{seasonID}/day/{dayID}/skiers    
**Meaning**: 
get number of unique skiers at resort/season/day  
**Data type**: Sets  
**Key**: serviceId + "re" + resortId + "s" + seasonId + "d" + daysId + "users"  
**Value**: a Set of userId String  
**Operations**:SADD, SREM, SMEMBERS, SCARD   
**Update**: Insert element to the Set  
**Query**: Use SCARD to query the number of elements in the Set.  

GET/skiers/{resortID}/seasons/{seasonID}/days/{dayID}/skiers/{skierID}  
**Meaning**: 
get the total vertical for the skier for the specified ski day  
**Data type**: Hashes  
**Key**: "re" + resortId + "u" + userId + "s" + seasonId + "vert"  
**Field**: dayId  
**Value**: a String representing a number  
**Operations**:HGETALL, HGET, HINCRBY   
**Update**: Use HINCRBY to add the vertical number   
**Query**: Use HGET to get one day record value.  

GET/skiers/{skierID}/vertical  
**Meaning**: 
get the total vertical for the skier the specified resort. If no season is specified, return all seasons    
**Data type**: Hashes  
**Key**: "re" + resortId + "u" + userId + "vert"    
**Field**: seasonId  
**Value**: a String representing a number  
**Operations**:HGETALL, HGET, HINCRBY   
**Update**: Use HINCRBY to add the vertical number   
**Query**: Use HGET to get one season record value if the season is specified. Use HGETALL to get all records if season is not specified.   
