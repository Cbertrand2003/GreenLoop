# GreenLoop – A Sustainable E-Commerce Platform

GreenLoop is a **role-based sustainable e-commerce platform** built with **Java Servlets, JSP, and MySQL**.  
It enables **customers, vendors, and administrators** to buy, sell, and manage eco-certified products while promoting green consumer habits.

---

## Features

###  Customers
- Register & login securely  
- Browse, search & filter sustainable products  
- Add/remove items in cart & checkout securely  
- Track orders & download PDF invoices  
- Submit product reviews & ratings  

###  Vendors
- Register & login as a seller  
- Add, edit, delete product listings  
- Inventory tracking with **low stock alerts**  
- Manage & fulfill customer orders  

###  Administrators
-  Approve or reject vendor product submissions  
-  Manage users & roles  
-  Generate reports (sales, sustainability, usage trends)  

---

##  Tech Stack
- **Frontend:** JSP, HTML5, CSS3, Bootstrap, JSTL  
- **Backend:** Java Servlets (MVC)  
- **Database:** MySQL 8+  
- **Server:** Apache Tomcat 9  
- **Libraries:**  
  - JDBC (DB connection)  
  - iText (PDF invoices)  
  - Log4J (logging)  
  - JUnit (unit testing)  
  - JavaMail (notifications, optional)  

---

##  Project Structure

```bash
GreenLoop/
├── README.md                # Project documentation
├── /src                     # Java source code
│   ├── model/               # Entity classes (User, Product, Order, Review, etc.)
│   ├── dao/                 # DAO interfaces + implementations
│   ├── service/             # Business logic (UserService, OrderService, etc.)
│   ├── controller/          # Servlets (LoginServlet, ProductServlet, etc.)
│   └── util/                # DBConnection, helpers
│
├── /web                     # Web content (JSP + static)
│   ├── index.jsp
│   ├── login.jsp
│   ├── register.jsp
│   ├── cart.jsp
│   ├── checkout.jsp
│   ├── profile.jsp
│   └── adminDashboard.jsp
│
├── /db                      # Database scripts
│   ├── schema.sql
│   └── seed.sql
│
├── /docs                    # Documentation & diagrams
│   ├── UML_Class_Diagram.png
│   ├── ERD.png
│   └── Architecture.png
│
└── /lib                     # External libraries (JDBC driver, iText, Log4J, etc.)
