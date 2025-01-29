# 2. Method Security

## API Usage

**GET `/videos`:**

```bash
curl --user <user>:<password> --location 'http://localhost:8080/videos[?search=<search_text>]'
```

**POST `/videos`:**

```bash
curl --user <user>:<password> \
--location 'localhost:8080/videos' \
--header 'Content-Type: application/json' \
--data '{
    "name": <name>,
    "description": <description>
}'
```

**DELETE `/videos/{id}`:**

```bash
curl --location --request DELETE 'localhost:8080/videos/:id' \
--user <user>:<password>
```
