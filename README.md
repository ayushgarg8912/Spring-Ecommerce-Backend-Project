# 🛒 E-Commerce Backend API

A scalable, secure, and production-ready **E-Commerce Backend Application** built using **Java, Spring Boot, Hibernate, and MySQL**.

This backend system powers a complete online shopping platform with features like authentication, product management, cart & wishlist handling, order processing, payment integration, email notifications, seller management, and advanced admin controls.

Designed with clean architecture principles and scalable backend practices to simulate real-world enterprise-level e-commerce systems.

---

# 🚀 Tech Stack

## 🔹 Backend Technologies
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Hibernate / JPA
- RESTful APIs
- Maven

## 🔹 Database
- MySQL

## 🔹 Payment Gateways
- Razorpay
- Stripe

## 🔹 Additional Tools & Libraries
- JavaMailSender
- Lombok
- Postman
- Validation API
- ModelMapper

---

# ✨ Core Features

# 🔐 Authentication & Security

- Secure Authentication using **Spring Security + JWT**
- Stateless Authorization Mechanism
- Custom JWT Token Generation & Validation
- Role-Based Access Control (RBAC)
- Password Encryption using BCrypt
- Protected APIs with Authentication Filters
- Secure Session Authority Parsing
- Custom Security Configuration using `@Configuration`

## 👥 Supported Roles
- 👤 Customer
- 🛍️ Seller
- 🛠️ Admin

---

# 👤 User Functionalities

## 👤 Customer Features
- Register/Login securely
- Browse products by category
- Search & filter products
- Add products to cart
- Manage wishlist
- Place orders
- Track order status
- Secure online payments
- Receive email notifications

---

## 🛍️ Seller Features
- Add & manage products
- Upload multiple product images
- Manage stock & inventory
- Update product pricing
- View seller-specific orders
- Access seller dashboard APIs

---

## 🛠️ Admin Features
- Manage users & sellers
- Block/unblock users
- Manage homepage banners
- Manage deals & offers
- Category management
- Product moderation
- Platform-wide administrative authority

---

# 📦 Product Management System

- Dynamic Product Architecture
- Multiple Product Images using `@ElementCollection`
- Category Mapping using `@ManyToOne`
- Seller-Product Relationship Management
- Inventory & Stock Tracking
- Product Availability Handling
- Discount & Pricing Support

---

# 🛒 Cart Management

- Add/Remove products from cart
- Quantity update support
- Dynamic price calculations
- Total order amount generation
- Persistent user cart management

---

# ❤️ Wishlist System

- `@OneToOne` relationship between User & Wishlist
- `@ManyToMany` mapping for favorite products
- Add/remove products dynamically
- Personalized shopping experience

---

# 📦 Order Management

- Create & place orders
- Order status tracking
- Payment-linked order verification
- Order history management
- Seller order processing system

## Order Status Flow
```text
PLACED → CONFIRMED → SHIPPED → DELIVERED
```

---

# 💳 Payment Gateway Integration

Integrated secure online payments using:

- Razorpay
- Stripe

## Payment Features
- Secure transaction processing
- Payment verification
- Payment status tracking
- Failed payment handling
- Order-payment linking

---

# 📧 Email Notification System

Integrated `JavaMailSender` for:

- OTP Verification
- Registration Emails
- Order Confirmation
- Payment Success Notifications
- Order Status Updates
- Account Security Alerts

---

# 🌐 REST API Architecture

Designed using clean RESTful architecture principles.

## API Mapping Annotations

```java
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
```

## API Modules
- Authentication APIs
- Product APIs
- Cart APIs
- Wishlist APIs
- Order APIs
- Payment APIs
- Seller APIs
- Admin APIs

---

# 🧠 Advanced Backend Concepts Used

- Dependency Injection
- Layered Architecture
- DTO Pattern
- Exception Handling
- Global Exception Management
- Service-Repository Pattern
- Custom Response Handling
- Validation using Annotations
- Clean Code Practices

---

# 🔥 Additional Features Added

## ✅ Product Search & Filtering
- Search products by:
  - Name
  - Category
  - Price
  - Seller

## ✅ Pagination & Sorting
- Paginated API responses
- Sorting by:
  - Price
  - Latest Products
  - Ratings

## ✅ Exception Handling
- Centralized exception handling using:
```java
@ControllerAdvice
```

## ✅ API Validation
Implemented request validation using:

```java
@NotNull
@NotBlank
@Valid
```

## ✅ Secure Password Storage
Passwords encrypted using:
```java
BCryptPasswordEncoder
```

## ✅ Scalable Layered Architecture
```text
Controller → Service → Repository → Database
```

---

# 📂 Project Structure

```bash
src
 ┣ 📂 controller
 ┣ 📂 service
 ┣ 📂 repository
 ┣ 📂 model
 ┣ 📂 dto
 ┣ 📂 config
 ┣ 📂 security
 ┣ 📂 exception
 ┣ 📂 response
 ┣ 📂 utils
 ┗ 📂 enums
```

---

# ⚙️ Installation & Setup

## 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/ecommerce-backend.git
cd ecommerce-backend
```

---

## 2️⃣ Configure Database

Update `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 3️⃣ Run Application

```bash
mvn spring-boot:run
```

Application starts at:

```bash
http://localhost:8080
```

---

# 🧪 API Testing

Use:
- Postman
- Swagger UI (Optional)

---

# 📈 Scalability & Performance

This project is designed keeping scalability in mind:

- Stateless JWT Authentication
- Layered Architecture
- Optimized Database Relationships
- Reusable Services
- Modular API Design

---

# 📌 Future Improvements

- Docker Deployment
- Microservices Architecture
- Redis Caching
- Elasticsearch Integration
- AI-based Product Recommendations
- Real-time Notifications using WebSockets
- Kafka Event Streaming
- Kubernetes Deployment
- CI/CD Pipeline Integration
- Cloud Deployment (AWS)

---

# 🤝 Contributing

Contributions are welcome!

## Steps:
1. Fork the repository
2. Create a new branch
3. Commit your changes
4. Push your branch
5. Create a Pull Request

---

# 📜 License

This project is licensed under the MIT License.

---

# 👨‍💻 Developer

# Ayush Garg
### Java Backend Developer

Passionate about building scalable and secure backend systems using **Java & Spring Boot**.

---

# 🔗 Connect With Me

- GitHub: https://github.com/ayushgarg8912
- LinkedIn: Add Your LinkedIn Here
- Email: Add Your Email Here

---

# ⭐ Support

If you like this project, consider giving it a ⭐ on GitHub!

---
