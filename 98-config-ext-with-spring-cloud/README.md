#  98. Config Externalization with Spring Cloud

This project demonstrates how to externalize configuration using Spring Cloud Config Server.

## Scripts

### Run Config Server

```bash
./gradlew :98-config-ext-with-spring-cloud:bootRun
```

## API Usage

**GET `/client/property`**

```bash
curl --location --request GET 'http://localhost:8888/client/property'
```
