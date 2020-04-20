Currently this library converts from any timezone to GMT.

Core Idea:

Let us suppose

Input:
0 30 17 ? * MON-FRI

Output
0 0 12 ? * MON-FRI

The logic here is it will take minutes, hours, day one by one find out next fire time as a date.
Then date gets parsed into GMT timezone
Then from there we will extract minutes, hours, day corresponding values we are calculating.


//Further work needs to be done

1. Month calculation
2. Day of week calculation
3. Unit testing
4. Cleaning pom.xml
5. Find ways to push library