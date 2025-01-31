# 5. Configure the Application

## How to run with profiles

### With normal profile

```bash
./gradlew :05-configure-app:bootRun
```

To sign in, see the list of users in `src/main/resources/application.properties`.

### With the `dev` profile

```bash
./gradlew :05-configure-app:bootRun -Dspring.profiles.active=dev
```

Or with the following command

```bash
export SPRING_PROFILES_ACTIVE=dev && ./gradlew :05-configure-app:bootRun
```

To sign in, see the list of users in `src/main/resources/application-dev.properties`.
