# 5. Configure the Application

Configuring an Application with Spring Boot

## How to run with profiles

### With default profile (implicitly `dev` profile)

```bash
./gradlew :05-configure-app:bootRun
```

To sign in, see the list of users in `src/main/resources/application.properties`.

### With the `test` profile

```bash
./gradlew :05-configure-app:bootRun -Dspring.profiles.active=test
```

Or with the following this Unix command

```bash
SPRING_PROFILES_ACTIVE=test ./gradlew :05-configure-app:bootRun
```

To sign in, see the list of users in `src/main/resources/application-test.properties`.
