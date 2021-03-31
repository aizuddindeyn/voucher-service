# voucher-service

**voucher-service** is ***Springboot*** powered microservice that allow configuration of promotion and voucher, that consists of features like generating new promotion, registering user as promotion end-user, and generating voucher(s) for said end-user.

> Voucher is a bond of the redeemable transaction type which is worth a certain monetary value and which may be spent only for specific reasons or on specific goods.

**Voucher** nowadays is famous in e-commerce company would try to expand their customer-based, by giving out attractive discounts.

### Features
- REST API to generate promotion and query/register user
- REST API to generate, validate and redeem voucher.
- Internal batch task to generate voucher(s), to improve processing time.
- Localization support in REST API

## Technology
sms-service uses a number of open source projects/libraries to work properly:
- [Springboot](https://spring.io/projects/spring-boot) - Stand-alone Spring based application (2.3.5.RELEASE)
- [Hibernate](https://hibernate.org/) - ORM framework between Java application and relational database (5.4.22.Final)
- [Spring JPA](https://spring.io/projects/spring-data-jpa) - Another example of persistence framework (2.3.5.RELEASE)
- [Log4J](https://logging.apache.org/log4j/2.x/) - Logging and tracing framework for auditing (2.13.3)
- (Optional) [H2 Database](https://www.h2database.com/html/main.html) - Embedded/In-memory database for Java application (1.4.200)

## Usage
Download and install dependencies, and start **voucher-service** server (can be executed at root folder)
```gradle
gradle bootRun
```
Request can be made by using API tools (e.g. Postman, SoapUI) or executing curl command
```sh
curl --location --request POST 'http://localhost:8080/voucher/1.0/offer/generate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "header": {
        "function": "offer.generate",
        "version": "1.0",
        "requestMsgId": "4f63d435-73f9-4390-ba6c-ec3cd2d8ce42",
        "requestTime": "2020-11-12 01:17:29.723"
    },
    "body": {
        "name": "MXS 10% Voucher",
        "prefix": "MXS",
        "codeLength": 10,
        "discount": 10,
        "expiry": "2021-12-31"
    },
    "signature": ""
}'
```

### Sample Request
```json
{
    "header": {
        "function": "offer.generate",
        "version": "1.0",
        "requestMsgId": "4f63d435-73f9-4390-ba6c-ec3cd2d8ce42",
        "requestTime": "2020-11-12 01:17:29.723"
    },
    "body": {
        "name": "Maxis 10% Voucher",
        "prefix": "MXS",
        "codeLength": 10,
        "discount": 10,
        "expiry": "2021-12-31"
    },
    "signature": ""
}
```
### Sample Response
```json
{
    "header": {
        "function": "offer.generate",
        "version": "1.0",
        "requestMsgId": "4f63d435-73f9-4390-ba6c-ec3cd2d8ce42",
        "requestTime": "2020-11-12 01:17:29.723"
    },
    "body": {
        "statusCode": "00",
        "message": "Success",
        "uniqueId": "4f63d435-73f9-4390-ba6c-ec3cd2d8ce43",
        "createdTime": "2020-11-12 01:17:29.800"
    },
    "signature": "offer.generate"
}
```

## Database
**voucher-service** was packaged together with in-memory H2 database, to allow stand-alone application.   
But, it also can be configured to connect with stand-alone relational database.  
Configure Spring data source properties inside ***application.properties***
```properties
# Database properties
spring.datasource.url=jdbc:h2:mem:sms_service
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=sa
spring.datasource.platform=h2
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=none
```

## Development
Pull request are welcome. Do contact me to learn more.

## License
MIT