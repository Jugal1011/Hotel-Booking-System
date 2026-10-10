# 🏨 Hotel Booking System

A backend REST API for hotel booking, built with **Spring Boot**, **PostgreSQL** and **JWT authentication**. Hotel managers list hotels and rooms, guests search for availability and book rooms, and prices adjust automatically with a **dynamic pricing engine** (Decorator pattern).

---

## ✨ Features

- **Authentication and authorization:** signup and login with BCrypt passwords, short-lived JWT access tokens, long-lived refresh tokens in an HttpOnly cookie, and role-based access (`GUEST`, `HOTEL_MANAGER`).
- **Hotel and room management (admin):** create, update, delete and activate hotels, and manage rooms per hotel.
- **Day-wise inventory:** activating a hotel (or adding a room to an active hotel) creates one `Inventory` row per room per day for the next year.
- **Search:** find hotels by city, date range and number of rooms, with pagination. A second endpoint returns each hotel's cheapest price.
- **Booking flow:** initialise a booking (rooms are reserved under a pessimistic DB lock), then add guests.
- **Dynamic pricing:** surge, occupancy, urgency and holiday strategies, recalculated hourly by a scheduler.
- **Uniform API responses:** every response is wrapped in `ApiResponse` with a timestamp, data and error. Errors are handled centrally.

## 🧰 Tech Stack

| Area | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot (Web MVC, Data JPA, Security) |
| Database | PostgreSQL |
| Auth | JWT (jjwt 0.13.0), BCrypt |
| Mapping | ModelMapper 3.2.6 |
| Boilerplate | Lombok |
| Build | Maven (wrapper included) |

---

## 🗂 Project Structure

```
src/main/java/com/hotelBookingSystem
├── HotelBookingSystemApplication.java   # entry point (@EnableScheduling)
├── advice/        # GlobalExceptionHandler, GlobalResponseHandler, ApiResponse, ApiError
├── config/        # MapperConfig (ModelMapper bean)
├── controller/    # REST controllers
├── dto/           # request/response objects
├── entity/        # JPA entities
├── enums/         # BookingStatus, PaymentStatus, Gender, Role
├── exception/     # ResourceNotFoundException
├── repository/    # Spring Data JPA repositories
├── security/      # JWTService, JWTAuthFilter, AuthService, WebSecurityConfig
├── service/       # business logic (+ PricingUpdateService scheduler)
└── strategy/      # dynamic pricing (Decorator pattern)
```

| Method | Endpoint | Access | Description |
|---|---|---|---|
| POST | `/auth/signup` | Public | Register a user (role `GUEST`) |
| POST | `/auth/login` | Public | Returns access token; sets `refreshToken` cookie |
| POST | `/auth/refresh` | Public (cookie) | Issue a new access token |
| GET | `/hotels/search` | Public | Search hotels by city, dates, rooms (paginated) |
| GET | `/hotels/searchWithMinPrice` | Public | Same search, with the cheapest price per hotel |
| GET | `/hotels/{hotelId}/info` | Public | Hotel details and its rooms |
| POST | `/bookings/init` | Authenticated | Reserve rooms and create a booking |
| POST | `/bookings/{bookingId}/addGuests` | Authenticated | Attach guests to a booking |
| POST | `/admin/hotels` | `HOTEL_MANAGER` | Create a hotel (inactive) |
| GET | `/admin/hotels/{hotelId}` | `HOTEL_MANAGER` | Get a hotel |
| PUT | `/admin/hotels/{hotelId}` | `HOTEL_MANAGER` | Update a hotel |
| DELETE | `/admin/hotels/{hotelId}` | `HOTEL_MANAGER` | Delete a hotel, its rooms and inventory |
| PATCH | `/admin/hotels/{hotelId}/activate` | `HOTEL_MANAGER` | Activate a hotel and generate a year of inventory |
| POST | `/admin/hotels/{hotelId}/rooms` | `HOTEL_MANAGER` | Add a room |
| GET | `/admin/hotels/{hotelId}/rooms` | `HOTEL_MANAGER` | List rooms of a hotel |
| GET | `/admin/hotels/{hotelId}/rooms/{roomId}` | `HOTEL_MANAGER` | Get a room |
| DELETE | `/admin/hotels/{hotelId}/rooms/{roomId}` | `HOTEL_MANAGER` | Delete a room and its inventory |

**Sample search body**
```json
{
  "city": "Vadodara",
  "startDate": "2026-11-01",
  "endDate": "2026-11-03",
  "roomsCount": 1,
  "page": 0,
  "size": 10
}
```

**Sample booking body**
```json
{
  "hotelId": 1,
  "roomId": 1,
  "checkInDate": "2026-11-01",
  "checkOutDate": "2026-11-03",
  "roomsCount": 1
}
```

**Response envelope** (applied to every response)
```json
{
  "timeStamp": "2026-10-10T12:00:00",
  "data": { },
  "error": null
}
```

Authenticated requests need the header `Authorization: Bearer <accessToken>`.

---

## 🚀 Getting Started

### Prerequisites
- JDK 21
- PostgreSQL running locally
- Maven (or use the included `./mvnw`)

### Setup

```bash
# 1. Clone
git clone https://github.com/Jugal1011/Hotel-Booking-System.git
cd Hotel-Booking-System

# 2. Configure the database and JWT secret in src/main/resources/application.properties
#    spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
#    spring.datasource.username=postgres
#    spring.datasource.password=<your-password>
#    jwt.secretKey=<a long random string, 32+ chars>

# 3. Run (tables are auto-created: ddl-auto=update)
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

### Becoming a hotel manager
Signup always assigns the `GUEST` role. To use the `/admin/**` endpoints, grant the role in the database:

```sql
INSERT INTO app_user_roles (user_id, roles) VALUES (<user_id>, 'HOTEL_MANAGER');
```
(The join table name can vary with your Hibernate naming; check the generated schema.)

### Typical flow
1. `POST /auth/signup`, then `POST /auth/login` for an access token.
2. As a manager: create a hotel, add rooms, then activate the hotel.
3. As a guest: search hotels, `POST /bookings/init`, then `POST /bookings/{id}/addGuests`.

---

## ⚠️ Current Status and Known Limitations

This project is work in progress. Things worth knowing:

- **Payments are not implemented yet.** The `Payment` entity and the `PAYMENTS_PENDING`, `CONFIRMED`, `CANCELLED` and `EXPIRED` states exist, but no endpoint or job uses them.
- **Booking amount is a placeholder.** It is fixed at `10` (see the `TODO` in `BookingServiceImpl`) instead of using the dynamic inventory price.
- **Booking expiry is checked only when adding guests** (10 minutes). Reserved stock is not automatically released.
- **Holiday pricing is a stub.** `isTodayHoliday` is hardcoded to `true`, so the 1.25x multiplier always applies.
- **Search endpoints use `GET` with a request body**, which many clients and proxies do not support. Consider query parameters or `POST`.
- **Admin endpoints are role-checked but not ownership-checked.** Any `HOTEL_MANAGER` can modify any hotel.
- **Inventory and day counts:** the date range is treated as inclusive of the check-out day.
- **`Booking.guests` is not initialised** in the builder, so adding guests may fail with a `NullPointerException` unless it is initialised.

## 🛣 Roadmap Ideas
- Payment integration and booking confirmation / cancellation
- Scheduled job to expire stale reservations and release stock
- Dynamic amount calculation from `Inventory.price`
- Real holiday calendar for pricing
- Request validation, OpenAPI/Swagger docs, and automated tests
- Docker / docker-compose setup

---

**Jugal**
- GitHub: [@Jugal1011](https://github.com/Jugal1011)
