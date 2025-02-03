# 98.1 Spring Cloud - Config Client

This project demonstrates how to externalize configuration using Spring Cloud Config Server.

## Scripts

### Run Config Client

> [!warning]
> Before running the client, please ensure the Config Server is running.
> See [98. Config Externalization with Spring Cloud](../98-config-ext-with-spring-cloud/README.md) for more information.

```bash
./gradlew :98_1-spring-cloud-config-client:bootRun
```

## API Usage

**GET `/client/property`**

```bash
curl --location --request GET 'http://localhost:8888/client/property'
```
