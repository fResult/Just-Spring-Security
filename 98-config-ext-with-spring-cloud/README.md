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

Response:

```json
{
  "name": "client",
  "profiles": [
    "property"
  ],
  "label": null,
  "version": "ba2af252a1b82ceb2b16d34e297b1c8e9b9b3146",
  "state": "",
  "propertySources": [
    {
      "name": "https://github.com/fResult/Spring-Configuration-Externalization/application.yml",
      "source": {
        "my.property": "This is a value from Centralized Configuration"
      }
    }
  ]
}
```
