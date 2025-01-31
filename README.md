# Just Spring Security

## Available Scripts

### For `01-my-spring-security` module

**To start the *01-my-spring-security* project, you can run:**

```bash
./gradlew :01-my-spring-security:bootRun
```

### For `02-method-security` module

**To start the *02-method-security* project, you can run:**

```bash
./gradlew :02-method-security:bootRun
```

### For `03-oauth` module
**Preparation Environment Variables**

```bash
cp 03-oauth/.envTemplate 03-oauth/.env
```

Then update the `.env` file with your own values.\
You can generate by creating credentials of OAuth Client ID.

- **Application Type:** Web Application
- **Name:** `Spring Security` (or any application name you want)
- **Authorized JavaScript origins:** (Optional) We can leave it blank
- **Authorized redirect URIs:** `http://localhost:8080/login/oauth2/code/okta`

**To start the *03-oauth* project, you can run:**

```bash
./gradlew :03-oauth:bootRun
```

### For `04-testing-with-junit5` module

**To start the *04-testing-with-junit5* project, you can run:**

```bash
./gradlew :04-testing-with-junit5:bootRun
```

### For `05-spring-security-test` module

**To start the *05-spring-security-test* project, you can run:**

**For normal run (let's say it is implicitly `production`):**

```bash
./gradlew :05-spring-security-test:bootRun
```

**For `dev` profile run:**

```bash
./gradlew :05-spring-security-test:bootRun -Dspring.profiles.active=dev
``` 

### For `99-event-driven-arch` module

**To try Event-Driven Architecture, you can run:**

```bash
./gradlew 99-event-driven-arch:run
```

## APIs Usage

### 01-my-spring-security

- See [README](./01-my-spring-security/README.md)

### 02-method-security

- See [README](./02-method-security/README.md)

### 03-oauth

- See [README](./03-oauth/README.md)

### 04-testing-with-junit5

- See [README](./04-testing-with-junit5/README.md)

### 05-spring-security-test

- See [README](./05-configure-app/README.md)
