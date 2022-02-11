# Spring Boot AWS SQS

This project demonstrates a simple microservice that uses Spring Boot and AWS SQS.

### Run project

``` docker build . -t spring-boot-aws-sqs ```


``` docker run -p 8080:8080 -e ACCESS_KEY=MYACCESSKEY -e SECRET_KEY=MYSECRETKEY -e REGION=us-east-1 -e QUEUE=MYQUEUENAME spring-boot-aws-sqs ``` 


### Example

Access Swagger and make a request: ``` http://localhost:8080/swagger-ui.html ```

![Alt text](docs/swagger.png?raw=true "Swagger Request")

Access AWS SQS console and see 'email_queue' queue:

![Alt text](docs/aws_sqs_console.png?raw=true "ActiveMQ Console")

Check message received on java output console:

![Alt text](docs/listener.png?raw=true "List")