DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

CREATE TABLE users(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL,
  password_hash VARCHAR(200) NOT NULL,
  salt VARCHAR(100) NOT NULL,
  role ENUM('CUSTOMER','VENDOR','ADMIN') NOT NULL
);

CREATE TABLE products(
  id INT PRIMARY KEY AUTO_INCREMENT,
  vendor_id INT NOT NULL,
  name VARCHAR(150) NOT NULL,
  description TEXT,
  price DECIMAL(10,2) NOT NULL,
  stock INT NOT NULL DEFAULT 0,
  sustainability_cert VARCHAR(100),
  FOREIGN KEY (vendor_id) REFERENCES users(id)
);

CREATE TABLE orders(
  id INT PRIMARY KEY AUTO_INCREMENT,
  customer_id INT NOT NULL,
  status VARCHAR(20) NOT NULL,
  created_at DATETIME NOT NULL,
  invoice_path VARCHAR(255),
  FOREIGN KEY(customer_id) REFERENCES users(id)
);

CREATE TABLE order_items(
  id INT PRIMARY KEY AUTO_INCREMENT,
  order_id INT NOT NULL,
  product_id INT NOT NULL,
  product_name VARCHAR(150) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  quantity INT NOT NULL,
  FOREIGN KEY(order_id) REFERENCES orders(id),
  FOREIGN KEY(product_id) REFERENCES products(id)
);

CREATE TABLE reviews(
  id INT PRIMARY KEY AUTO_INCREMENT,
  product_id INT NOT NULL,
  customer_id INT NOT NULL,
  rating INT NOT NULL,
  comment VARCHAR(500),
  date DATE NOT NULL,
  FOREIGN KEY(product_id) REFERENCES products(id),
  FOREIGN KEY(customer_id) REFERENCES users(id)
);

-- Seed users (passwords will be updated after you know salts; for demo, we pre-insert hashed examples)
INSERT INTO users(name,email,password_hash,salt,role) VALUES
('Admin','admin@greenloop.com','Q0n3Uu3ZJc7c6V2c0Q5o1hX5y2gkQhQ7Aq3z9r2i7Hk=','c2FsdGFkbWluMTIzNDU2Nzg5MA==','ADMIN'),
('Vendor','vendor@greenloop.com','Q0n3Uu3ZJc7c6V2c0Q5o1hX5y2gkQhQ7Aq3z9r2i7Hk=','c2FsdHZlbmRvcjEyMzQ1Njc4OTA=','VENDOR'),
('User','user@greenloop.com','Q0n3Uu3ZJc7c6V2c0Q5o1hX5y2gkQhQ7Aq3z9r2i7Hk=','c2FsdHVzZXIxMjM0NTY3ODkw','CUSTOMER');

-- Seed products
INSERT INTO products(vendor_id,name,description,price,stock,sustainability_cert) VALUES
(2,'Bamboo Toothbrush','Eco-friendly bamboo brush',4.99,20,'FSC'),
(2,'Reusable Water Bottle','Stainless steel insulated bottle',19.99,15,'EnergyStar'),
(2,'Organic Cotton Tote','Reusable shopping bag',7.49,12,'FairTrade');
