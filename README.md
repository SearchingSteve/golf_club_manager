# Golf Club Management System API

This is a Spring Boot application that provides a REST API for managing golf club members and tournaments.

## Prerequisites

- Docker and Docker Compose installed
- Postman (for API testing)

## Running the Application

### Using Docker (Recommended)

1. Clone the repository:

```bash
git clone <repository-url>
cd golf_club_manager
```

2. Build and start the containers:

```bash
docker-compose up --build
```

This will:

- Build the Spring Boot application
- Start a MySQL container
- Create the database and tables
- Start the application container
- Link the containers together

The application will be available at `http://localhost:8080`

### Manual Setup (Alternative)

If you prefer to run the application without Docker:

1. Install MySQL 8.0
2. Create a database:

```sql
CREATE DATABASE golf_club;
```

3. Configure the application:

   - Update the database credentials in `application.properties`

4. Build the application:

```bash
./mvnw clean package
```

5. Run the application:

```bash
java -jar target/golf-club-manager-0.0.1-SNAPSHOT.jar
```

## Database Configuration

### Docker Setup

The MySQL database is automatically configured with:

- Database Name: `golf_club`
- Username: `root`
- Password: `root`
- Port: `3307`

### Manual Setup

Update `src/main/resources/application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/golf_club
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Testing the API

### Using Postman Collection

1. Open Postman
2. Click "Import" and select the `Golf_Club_Management.postman_collection.json` file
3. The collection will be imported with all endpoints pre-configured

### Testing Order

1. Create a member using the "Create Member" endpoint
2. Create a tournament using the "Create Tournament" endpoint
3. Test GET operations to verify the created data
4. Test search operations
5. Test update operations


## Troubleshooting

### Docker Issues

1. If containers fail to start:

```bash
docker-compose down
docker-compose up --build
```

2. To check container logs:

```bash
docker-compose logs -f
```

3. To access MySQL container:

```bash
docker-compose exec db mysql -u root -p
```

### Database Issues

1. If database connection fails:

   - Check if MySQL container is running: `docker ps`
   - Verify database credentials in `application.properties`
   - Check MySQL logs: `docker-compose logs db`

2. To reset the database:

```bash
docker-compose down -v
docker-compose up --build
```

### License

This project is licensed for educational purposes only. Redistribution, modification, or commercial use requires prior written permission.

### Author

- **Stephen Crocker**
- GitHub: [SearchingSteve](https://github.com/SearchingSteve)