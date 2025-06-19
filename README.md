# 🛒 E-commerce Web Application with eSewa Payment Gateway

A full-featured Spring Boot-based E-commerce web application integrated with **eSewa**, Nepal's leading digital wallet for secure online payments.

---

## 📌 Features

- ✅ User registration & login (JWT secured)
- ✅ Role-based access (Admin & Customer)
- ✅ Product listing & category filtering
- ✅ Cart management and checkout
- ✅ Shipping address management
- ✅ Order placement and history tracking
- ✅ **Secure eSewa payment gateway integration**
- ✅ Admin panel for product and order management

---

## 🏗️ Tech Stack

| Layer        | Technology                      |
|--------------|----------------------------------|
| Backend      | Java, Spring Boot, Spring Security |
| Frontend     | Thymeleaf / React (depending on setup) |
| Database     | MySQL                            |
| ORM          | Hibernate (JPA)                  |
| Auth         | JWT (JSON Web Token)             |
| Payment      | eSewa (HmacSHA256 alogrithm  sha256_HMAC signature secured)|
| Migration    | Liquibase                        |
| Tools        | Maven, Postman, Git              |

---

## 🔐 eSewa Payment Gateway

### ✅ Flow:
1. User places an order → order is saved in the DB.
2. Redirects user to **eSewa payment page** with required details and a **secure hash signature**.
3. eSewa redirects back to your server with transaction result.
4. Server verifies the result and updates order/payment status.

### 🔒 Security:
- Uses **SHA-512 hash signature** for transaction integrity.
- Backend callback endpoint verifies payment using:
