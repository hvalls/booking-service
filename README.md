## Booking Service

### API docs

Copy and paste the [OpenAPI file](etc/openapi.yaml) to https://editor.swagger.io. 

### Packages structure

- `domain`: This package contains the domain model and logic. Domain uses interfaces to talk to the external elements (database, messaging systems...) 
  - `command`: Business logic and write models
  - `query`: Read models / projections.
  - `common`: Elements that both sides, command an query, use.
- `adapter`: This package contains the actual implementation of the primary/driving and secondary/driven adapters
  - `db`: Database (H2) implementations of BookingRepository and DAO (secondary adapters)
  - `event`: Event publisher and subscriber in memory implementations (secondary adapters)
  - `http`: REST primary adapter implementation (HTTP controller). It talks to the domain via primary/input ports (CommandGateway and QueryGateway)

### Create booking use case

![Create booking diagram](etc/create_booking_diagram.png)

### Get booking count use case

![Get booking count diagram](etc/get_booking_count_diagram.png)

### Run

```bash
$ ./graadlew bootRun
```

### Run with Docker

```bash
# Build Docker image
$ docker build -t booking-service .
# Run container
$ docker run -it -p 8080:8080 booking-service
```

### Example usage with cURL

```bash
# Create booking
$ curl -X POST http://localhost:8080/bookings -H 'Content-Type: application/json' -d '{"resourceId": "1234"}'

# Get booking count
$ curl http://localhost:8080/bookings/count
{"count": 1} 
```

### Run tests
```bash
$ ./gradlew test
```