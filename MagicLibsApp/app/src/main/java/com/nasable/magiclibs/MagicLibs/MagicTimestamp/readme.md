# MagicTimestamp

## Constructors

New instance with a now timestamp
 ```
MagicTimestamp magicTimestamp=MagicTimestamp.now();
 ```
 New instance *static alternative*  with a now timestamp
  ```
MagicTimestamp magicTimestamp=MagicTimestamp.now();
 ```
### Alternative constructors
 - **MagicTimestamp(long timestamp)**
 - **MagicTimestamp(String timestamp, String pattern)**
    - *timestamp*: the timestamp as a string
    - *pattern*  : the pattern that the passed string is written in ex: yyyy-MM-dd HH:mm:ss
- **MagicTimestamp(String timestamp)**
    - *timestamp*: the timestamp as a string with a yyyy-MM-dd HH:mm:ss pattern

## Methods

### Numbers
- **long getTimestamp()**
- **int getDateYear()**
- **int getDateMonth()**
- **int getDateDay()**
- **int getDateDayOfYear()**
- **int getDateWeekNumber()**

### Strings
- **String getYearYYYY()**
- **String getMonthMM()**
- **String getDayDD()**
- **String getHourMinute()**: HH:mm
- **String getDayMonth()**  : dd MMM
- **String getDayMonthYear()**  : dd MMM yy
- **String getSQLTimestamp()**  : yyyy-MM-dd HH:mm:ss
- **String getTimestampLocalFromUTC()**  : yyyy-MM-dd HH:mm:ss *Useful when using the sqlite timestamp*
- **String getUserFriendly()**  :  With regard to now()

### Pattern 
- **String getPatternTimeStamp(String pattern)**   
- **String getPatternTimestampWithTimeZone(String pattern,String timeZoneId)**   
- Patterns:
    - *PATTERN_DATETIME*  yyyy-MM-dd HH:mm:ss 
    - *PATTERN_ISO_8601*  yyyy-MM-dd'T'HH:mm'Z' 
    - *PATTERN_RFC_2822*  EEE, dd MMM yyyy HH:mm:ss Z' 
    - *PATTERN_RFC_3339*  yyyy-MM-dd'T'HH:mm:ssXXX' 
    - *PATTERN_RFC_882*   E, d MMM yyyy HH:mm:ss Z 
- Timezone ID:
    - *TIMEZONE_UTC*
    - Also: **String getDefaultTimeZoneId()**


### Difference functions
- **long getDiffMillis(MagicTimestamp magicTimestamp)** :  Difference in miliseconds
- **long getDiffSeconds(MagicTimestamp magicTimestamp)** :  Difference in seconds
- **long getDiffMinuets(MagicTimestamp magicTimestamp)**  
- **long getDiffHours(MagicTimestamp magicTimestamp)**  
- **int getDiffDays(MagicTimestamp magicTimestamp)**  
- **String getDiffHumanReadable(MagicTimestamp magicTimestamp)** 