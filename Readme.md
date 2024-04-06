# Shop Hub

General Purpose Shop Application

## Technologies

- Java 21
- Spring Framework
- MongoDB
- Tailwind (or bootstrap if tailwind won't work)

## Requirements

## Front-end

### File locations

| Extension | Location                             |
|-----------|--------------------------------------|
| *.html    | `src/main/resources/templates/`      |
| *.js      | `src/main/resources/static/scripts/` |
| *.svg     | `src/main/resources/static/svg/`     |

### Security

**WebSecurityConfig** is located in`com/university/shophub/frontend/security/WebSecurityConfig.java`

Where you can add:

- Public endpoints
- Private endpoints
- Endpoints with no CSRF protection

### Database
Link to MongoDB Configuration: <https://medium.com/mongoaudit/how-to-enable-authentication-on-mongodb-b9e8a924efac>

- install [MongoDB](https://www.mongodb.com/docs/manual/installation/) and [mongosh](https://www.mongodb.com/docs/mongodb-shell/install/)
- Run `mongod --port 27017`

- run `mongosh localhost:27017`

- Create `shophub_db` database

```mongodb-json
use shophub_db
```

- create a user in database

```mongodb-json 
 db.createUser(
  {
    user: "shophub_db_client",
    pwd: "secred_password",
    roles: [ { role: "readWrite", db: "shophub_db" } ]
  }
 )
```

- Enable security in `mongodb.cfg`

```yaml
security:
    authorization:"enabled"
```
