# GitHub User Repositories API

This API returns all public GitHub repositories of a given user that are not forks,
including information about branches and the latest commit SHA.

## Running

To run application use:

```bash
mvn spring-boot:run
```

## Testing

To run tests use:
```bash
mvn test
```

## Api Documentation


### `GET` `/{username}/repos`

#### Request

- **Path Parameter**:
    - `username` *(string)* – GitHub username

#### Response

- **Status**: `200 OK`
- **Body (application/json)**:

```json
[
  {
    "name": "e-learning-app",
    "ownerName": "m4teusz-korzeniowski",
    "branches": [
      {
        "name": "master",
        "lastCommitSha": "c70f357c174ffe3ada1e53b57d7f02753fcbbb67"
      }
    ]
  },
  {
    "name": "github-repos-display",
    "ownerName": "m4teusz-korzeniowski",
    "branches": [
      {
        "name": "master",
        "lastCommitSha": "cdbaf1493251f7ced71b700273caf7c42b41e410"
      }
    ]
  },
  {
    "name": "rpg-combat-manager",
    "ownerName": "m4teusz-korzeniowski",
    "branches": [
      {
        "name": "master",
        "lastCommitSha": "f2ddef2562c2a04613f0267a9fe2e069a139fbae"
      }
    ]
  }
]
```