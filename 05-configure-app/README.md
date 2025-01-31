# 5. Configure the Application

## How to run with profiles

### With normal profile

```bash
./gradlew :05-configure-app:bootRun
```

To sign in, see the list of users in `src/main/resources/application.properties`.

### With the `dev` profile

```bash
./gradlew :05-configure-app:bootRun -Dspring.profiles.active=test
```

To sign in, see the list of users in `src/main/resources/application-dev.properties`.
