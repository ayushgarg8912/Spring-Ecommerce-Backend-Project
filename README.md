🛒 E-Commerce Backend API - Spring Boot

A scalable and secure E-Commerce Backend application built using Java, Spring Boot, Hibernate, and MySQL.
This project provides complete backend support for an online shopping platform including authentication, product management, order processing, payments, admin controls, and vendor management

🚀 Tech Stack
Java
Spring Boot
Spring Security
JWT Authentication
Hibernate / JPA
MySQL
REST APIs
Maven
Razorpay Integration
Stripe Integration
JavaMailSender

✨ Features
🔐 Security & Authentication
Implemented secure authentication using Spring Security + JWT
Custom JWT token generation and validation mechanism
Role-based authorization and protected APIs
Configured custom AppConfig using @Configuration
User login returns JWT token with assigned roles
Session authority parsing using:

Supported Roles
Customer
Seller
Admin

👤 User Roles & Access Control
Customer
Add products to cart
Manage wishlist
Place orders
Track order status
Seller
Manage products and inventory
Access vendor-specific APIs
Update stock and pricing
Admin
Manage users and vendors
Control homepage metadata
Manage deals, banners, and categories
Global system authority

🗂️ Core Domain Architecture
📦 Product Management
Multiple product images managed using @ElementCollection
Category and seller relationships handled using @ManyToOne
Dynamic product structure for scalable inventory management
❤️ Wishlist System
@OneToOne mapping between User and Wishlist
@ManyToMany relationship for storing favorite products

🌐 REST API Design
Clean RESTful API architecture using:
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
🛠️ Admin APIs

Dynamic admin endpoints for:

Deals management
Banner updates
Homepage sections
Product controls
Category management

💳 Payment Gateway Integration

Integrated secure payment processing using:

Razorpay
Stripe

Features:

Payment verification
Secure transaction handling
Order payment tracking
📧 Email Notification System

Integrated JavaMailSender for:

OTP Verification
Registration Emails
Order Notifications
Payment Updates

📌 Future Improvements
Docker Deployment
Microservices Architecture
Redis Caching
Elasticsearch Integration
AI-based Product Recommendations
Real-time Notifications
🤝 Contributing

Contributions are welcome.
Fork the repository and create a pull request.

📜 License

This project is licensed under the MIT License.

👨‍💻 Developer

Ayush Garg
Java Backend Developer
Passionate about building scalable backend systems using Java & Spring Boot.
