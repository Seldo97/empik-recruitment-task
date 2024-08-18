# Marcin Olek Empik recruitment task

## 1. Host, Swagger-ui, H2 database resources:
The database file is created in the project's root directory, inside the data folder, after the first run of the application.

- JDBC URL: jdbc:h2:file:./data/app_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
- Username: sa
- Password:
```
Local Address: 			localhost:9090
Swagger UI: 			localhost:9090/swagger-ui.html
H2 db console: 			localhost:9090/h2-console
JDBC url: 			jdbc:h2:file:./data/app_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
```

## 2. If you want to run app by Docker instead of IDE:

1. Build project by Maven

   ```bash
   mvn clean install
   ```
2. Build Docker image

   ```bash
   docker build -t marcinolek:latest .
   ```

3. Run Docker container

   ```bash
   docker run -d -p 9090:9090 --name marcinolek-app marcinolek:latest
   ```
## 3. You can change properties in:
   ```
   application.yml
   application-docker.yml
   ```