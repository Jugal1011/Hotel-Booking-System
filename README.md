# 🏨 Hotel Booking System

A Spring Boot REST backend for hotel search, room booking, dynamic pricing, and online payments. 🚀

## ✨ Features

- 👤 **Sign up / Login** with BCrypt passwords
- 🔐 **Role-Based JWT Auth** – `GUEST` and `HOTEL_MANAGER` roles, short-lived access token + refresh token cookie
- 🏨 **Hotel & Room Management** – managers create hotels/rooms, activate them, and manage daily inventory
- 🔎 **City Search** – find hotels by city, dates, and rooms with pagination
- 💰 **Dynamic Pricing** – chained pricing rules (surge, occupancy, urgency, holiday) refreshed hourly by a scheduler 🧩
- 📅 **Safe Booking Flow** – `RESERVED → GUESTS_ADDED → PAYMENTS_PENDING → CONFIRMED / CANCELLED` with DB row locking to stop double-booking 🚫
- 💳 **Stripe Payments** – Checkout sessions, refunds on cancellation
- 🔔 **Stripe Webhook** – confirms the booking automatically after payment ✅
- 📊 **Manager Reports** – bookings list, confirmed count, total and average revenue
- 📘 **Swagger UI** – interactive API docs

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| 💻 Language | Java 21 |
| 🌱 Framework | Spring Boot 4 (Web, Data JPA, Security) |
| 🐘 Database | PostgreSQL |
| 🔑 Auth | JWT (JJWT) |
| 💳 Payments | Stripe |
| 🧰 Tools | Lombok, ModelMapper, springdoc-openapi, Maven |

## 🧠 How It Works

```
👤 Login ─▶ 🔐 JWT ─▶ 🔎 Search hotels ─▶ 📅 Init booking (rooms reserved + locked)
      ─▶ 👥 Add guests ─▶ 💳 Stripe Checkout ─▶ 🔔 Webhook ─▶ ✅ CONFIRMED
```

## 📁 Project Structure

```
src/main/java/com/hotelBookingSystem/
├── controller/   🎮 REST endpoints
├── service/      ⚙️ Business logic
├── strategy/     💰 Dynamic pricing rules
├── security/     🔐 JWT & Spring Security
├── entity/       🗃️ JPA models
├── repository/   🗄️ Data access
├── dto/          📦 Request/response objects
├── advice/       🚨 Global error & response handling
└── config/       ⚙️ CORS, Stripe, ModelMapper
```

## ⚙️ Getting Started

1. ☕ Install **Java 21** and **PostgreSQL**
2. 📥 Clone the repo
   ```bash
   git clone https://github.com/Jugal1011/Hotel-Booking-System.git
   ```
3. 📝 Copy `application-example.properties` to `application.properties`
4. 🔑 Set environment variables: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET_KEY`, `STRIPE_SECRET_KEY`, `STRIPE_WEBHOOK_SECRET`, `FRONTEND_URL`, `ADMIN_EMAIL`, `ADMIN_PASSWORD`
5. ▶️ Run the app
   ```bash
   ./mvnw spring-boot:run
   ```
6. 🔔 Test webhooks locally
   ```bash
   stripe listen --forward-to localhost:8080/api/v1/webhook/payment
   ```

API base path: `http://localhost:8080/api/v1`

On startup, the app creates a `HOTEL_MANAGER` account using `ADMIN_EMAIL` and `ADMIN_PASSWORD` if that email is not already registered. The password is BCrypt-hashed. Set both variables before starting the app; subsequent startups skip account creation when the configured email already exists.

When using Docker Compose, put `ADMIN_EMAIL` and a strong, unique `ADMIN_PASSWORD` in your local `.env` file. Compose requires both values and passes them to the application container; do not put credentials in the Dockerfile or commit `.env`.

## 👨‍💻 Author

**Jugal** – [@Jugal1011](https://github.com/Jugal1011)

⭐ If you like this project, give it a star!
