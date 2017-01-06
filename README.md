# email-digest
Hourly email digest

### Test Urls
* Ping url: http://ec2-52-210-211-77.eu-west-1.compute.amazonaws.com:8080/sns/receive/greeting

#### Installations
* Install JDK 8
* MongoDB

#### How to run
* Check out the code from https://github.com/Jayasagar/email-digest-collect
* Run **/.gradlew build** It should produce executable jar under build/libs
* Modify your AWS accesskey and secret key the **application.properties** which is located under project checkout root folder

##### Run as a service
* sudo mkdir /var/email-digest
* sudo cp $CHECK_OUT/build/libs/email-digest-0.1.jar /var/email-digest
* sudo cp $CHECK_OUT/application.properties /var/email-digest
* sudo ln -s /var/email-digest/email-digest-0.1.jar /etc/init.d/email-digest
* sudo /etc/init.d/email-digest start|stop|restart

##### Run a jar
* java -jar build/libs/email-digest-0.1.jar --spring.config.location= $CHECKOUT_ROOT_PATH/application.properties

### Current solution implemented using Spring cloud AWS, SNS HTTP subscription and MongoDB
* Send message from SNS to MongoDB for temporary message backup as system may go down.

##### Current execution flow
* Consume the message through HTTP(Spring Cloud AWS messaging) from AWS SNS
* Write the message to MongoDB

#### Why MongoDB
* Primary reason: If we get huge amount of data, we can spin up more instances and then it would be easily scale across the cluster!!

### Solution using Flink vs MongoDB
* https://github.com/okkam-it/flink-mongodb-test
* http://robertmetzger.de/incubator-flink-website/news/2014/01/28/querying_mongodb.html
* https://github.com/rmetzger/flink-mongodb-example/blob/master/src/main/java/org/apache/flink/Job.java

### Solution using AWS EMR
* 

### Solution using SNS vs SQS Subscription 
##### Pros
* Messages can be backup for few days out of the box so that never worry about downtime.
* 
##### Cons
* Amazon SNS isn't currently compatible with SQS FIFO queues. For us order matters as per my understanding.
* Not sure number of message can be processed by SQS, what if SNS produces huge messages and to consider we process once in an hour!

### Solution using Spring cloud AWS, SNS HTTP subscription and File System 
##### Pros
* Messages can be streamed to file in specific format and we can streams back to process after every 1 hour
* 
##### Cons
* Hard to query
* Distributed locking?
* 

### Solution using Spring cloud AWS, SNS HTTP subscription and RabbitMQ 
##### Pros
* No Db, so performance can be gain
* 
##### Cons
* Messaging order issue, need to put efforts!!

#### External sort 
* could be good candidate for processing huge data file

### Local
* java -jar build/libs/email-digest-0.1.jar --spring.config.location=/email-digest/application.properties

### References
* http://docs.spring.io/spring-boot/docs/1.3.0.RC1/reference/htmlsingle/#deployment-script-customization-conf-file
* https://github.com/FasterXML/jackson-modules-java8
* http://engineering.pamediakopes.gr/2015/10/12/sns-a-love-and-hate-story/  
* https://www.quora.com/What-is-the-difference-between-Kinesis-and-SQS-It-seems-capable-of-serving-similar-use-cases-apart-from-the-shards-and-partition-keys
 