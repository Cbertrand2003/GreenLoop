
#  GreenLoop – A Sustainable E-Commerce Platform

**GreenLoop** is a Java-based web platform built to promote sustainable consumer behavior by enabling users to buy and sell environmentally certified products. The system supports customers, vendors, and administrators with secure login, product management, order tracking, and administrative tools.

---
## Project Goals

GreenLoop is designed to:
- Encourage sustainable shopping habits
- Support small eco-friendly vendors
- Educate users on environmentally certified products

##  Features

- **User Authentication & Session Management**
  - Login/logout functionality using Java Servlets
  - Session control with role-based access (Customer, Vendor, Admin)

- **Vendor Product Management** *(In Progress)*
  - Vendors can add, edit, or delete their products
  - Interface built with `vendorProducts.jsp`

- **Cart & Order Flow**
  - Customers can add items to cart and track orders
  - Orders are tied to sessions and persisted in the system

- **Admin Panel & Notifications**
  - Admins approve vendors and moderate product listings
  - Observer pattern implemented for sending email notifications

- **PDF Invoices & Email Confirmations**
  - iText used to generate PDF invoices after checkout
  - JavaMail API used to send confirmation emails

---

##  Folder Structure

```
GreenLoop/
├── GreenLoop/           # Java source files (Servlets, DAOs, Models)
├── db/                  # MySQL setup scripts (setup.sql)
├── src/                 # Additional Java source files
├── web/                 # JSP pages and frontend assets
├── bin/                 # Compiled class files (optional)
├── .vscode/             # VS Code config
├── README.md            # Project documentation
```

---

##  Setup Instructions

### 1. Clone the Repository

```
git clone https://github.com/Cbertrand2003/GreenLoop.git
```

### 2. Set Up the Database

- Start MySQL server
- Create a new database:

```
CREATE DATABASE greenloop;
USE greenloop;
```

- Run the `db/setup.sql` script to create the required tables

### 3. Deploy the Application

- Compile Java files in `GreenLoop/` and `src/`
- Deploy using a `.war` file to Tomcat, or
- Use VS Code’s Servlet Runner extension
- Open browser at: `http://localhost:8080/GreenLoop`

---

## Technologies Used

- Java 
- MySQL
- Apache Tomcat
- GitHub
- Visual Studio Code

---

## Team Contributions

- **Deniz Capan** – User Authentication (LoginServlet, LogoutServlet, UserDAO, login.jsp)
- **Joseph Simpunga** – Vendor Product Management (ProductServlet, ProductDAO, vendorProducts.jsp)
- **Cedric Bertrand** – Cart & Order Flow (CustomerCartServlet, ShoppingCart.java, Order.java, cart.jsp)
- **Kenneth Saunders** – Admin & Notifications (AdminApprovalServlet, NotificationService.java, dashboard.jsp)
- **Joseph Bueti** – GitHub Repository, Documentation, Final Report, Video Presentation

---

## Git Branches

- `main` – Base branch
- `master` – Cart & Order Flow updates
- `user-auth-feature` – Login + DB setup
- `GreenLoopAdmin` – Admin logic
- `github.com/DenizCapan/GreenLoop` – DB + login test branch

---

## License

This project was created for the CST8319 Software Development Project course at Algonquin College and is not licensed for commercial use.

---

## Repository

[GreenLoop GitHub Repo](https://github.com/Cbertrand2003/GreenLoop)
