
# Testing virtual threads performance

We are trying to test the performance of virtual threads as compared to native threads with java 21 and springboot 3.




## Benchmark Task

Running `select sleep(1)` on mysql

## How to run the application

#### Mysql setup
Can choose any Mysql DB that you already have and can update host/db name/username/password in the application.properties file accordingly

Don't need to create any tables in the database as this query does not interact with any tables.

#### Commands

build: `./gradlew clean build`

run: `java -jar build/libs/demo-0.0.1-SNAPSHOT.jar`


## Load test script
You can change `rateLimit` variable for adjusting the concurrency. The script call the API 2-3 times to warm up the connection pool at the application side

#### Commands

`cd script` - move to script folder

build: `go build -o main`

run: `./main`