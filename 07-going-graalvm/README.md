# 7. Going GraalVM

Going Native with Spring Boot

## Table of Contents

- [Pre-requisite Software](#pre-requisite-software)
- [Scripts](#scripts)
  - [Run Built JAR as Scaled Application](#run-built-jar-as-scaled-application)
    - [Profile `instance1`](#profile-instance1)
    - [Profile `instance2`](#profile-instance2)
    - [Profile `instance3`](#profile-instance3)
  - [Run Containerized Application](#run-containerized-application)
  - [Build Image](#build-image)
  - [Push Built Image to GitHub Container Registry](#push-built-image-to-github-container-registry)
  - [Pull Image from GitHub Container Registry](#pull-image-from-github-container-registry)

## Pre-requisite Software

- Docker (or your favorite containerization platform)

## Scripts

### Prepare the Application

Prepare the `.env` file by copying the `.envTemplate` file

```bash
cp 06-release-app/.envTemplate 06-release-app/.env
```

### Run Built JAR as Scaled Application

#### Profile `instance1`

1. Open a terminal and run the following command

    ```bash
    SPRING_PROFILES_ACTIVE=instance1 java -jar 06-release-app/build/libs/06-release-app-0.0.1.jar
    ```

2. Then GET the /videos endpoint by running the following command

    ```bash
    curl localhost:9001/videos --user <username>:<password>
    ```

#### Profile `instance2`

1. Open another terminal and run the following command

    ```bash
    SPRING_PROFILES_ACTIVE=instance2 java -jar 06-release-app/build/libs/06-release-app-0.0.1.jar
    ```

2. Then GET the /videos endpoint by running the following command

    ```bash
    curl localhost:9002/videos --user <username>:<password>
    ```

#### Profile `instance3`

1. Open the 3rd terminal and run the following command

    ```bash
    SPRING_PROFILES_ACTIVE=instance3 java -jar 06-release-app/build/libs/06-release-app-0.0.1.jar
    ```

2. Then GET the /videos endpoint by running the following command

    ```bash
    curl localhost:9003/videos --user <username>:<password>
    ```

### Build Image

```bash
./gradlew :06-release-app:bootBuildImage
```

### Push Built Image to GitHub Container Registry

You can push to your GitHub Container Registry by running the following command

First, we need to login to GitHub Container Registry

```bash
echo <github_personal_access_token> | docker login ghcr.io -u <your_github_username>
```

For `<github_personal_access_token>` is your GitHub Personal Access Token, generated from *GitHub Developer Settings*.

Second, tag and push the image to GitHub Container Registry

```bash
# Tag the image with the GitHub Container Registry URL
docker tag 06-release-app:0.0.1 ghcr.io/<github_username>/06-release-app:0.0.1

# Push the image to GitHub Container Registry 
docker push ghcr.io/<github_username>/06-release-app:0.0.1
```

**You may see your image in the GitHub Container Registry something like this:**
<https://github.com/fResult/Just-Spring-Security/pkgs/container/06-release-app/349742251?tag=0.0.1>

### Pull Image from GitHub Container Registry

For testing purposes, we should remove the image `06-release-app:0.0.1` from the local machine before pulling it from GitHub Container Registry

```bash
docker image rm 06-release-app:0.0.1
```

Then, pull the image from GitHub Container Registry

```bash
docker pull ghcr.io/<github_username>/06-release-app:0.0.1
```

Then, display image we just pulled, we should see the image `ghcr.io/<github_username>/06-release-app:0.0.1`

```console
➜ docker images | grep -E "IMAGE|06"
REPOSITORY                                 TAG              IMAGE ID       CREATED         SIZE
ghcr.io/fresult/06-release-app             0.0.1            27354db5e084   45 years ago    297MB
```

#### Run Containerized Application

**Profile `instance1`:**

```bash
docker run -p 9001:9001 -e SPRING_PROFILES_ACTIVE=instance1 ghcr.io/<github_username>/06-release-app:0.0.1
```

**Profile `instance2`:**

```bash
docker run -p 9002:9002 -e SPRING_PROFILES_ACTIVE=instance2 ghcr.io/<github_username>/06-release-app:0.0.1
```

**Profile `instance3`:**

```bash
docker run -p 9003:9003 -e SPRING_PROFILES_ACTIVE=instance3 ghcr.io/<github_username>/06-release-app:0.0.1
```

Then, we can test the application in the same way as we did in the section [Run Built JAR as Scaled Application](#run-built-jar-as-scaled-application).
