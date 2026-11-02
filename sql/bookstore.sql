-- Run in phpMyAdmin (XAMPP) -> SQL tab
CREATE DATABASE IF NOT EXISTS bookstore_db;
USE bookstore_db;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,       -- SHA-256 hash
    role VARCHAR(20) DEFAULT 'ADMIN'
);

CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    price DOUBLE NOT NULL,
    quantity INT NOT NULL
);

-- Login: admin / admin123
INSERT INTO users (username, password) VALUES ('admin', SHA2('admin123', 256))
ON DUPLICATE KEY UPDATE username = username;

INSERT INTO books (title, author, category, price, quantity) VALUES
('Wings of Fire', 'A.P.J. Abdul Kalam', 'Biography', 299.00, 25),
('The Alchemist', 'Paulo Coelho', 'Fiction', 350.00, 40),
('Clean Code', 'Robert C. Martin', 'Technology', 899.00, 15),
('Let Us C', 'Yashavant Kanetkar', 'Technology', 450.00, 30),
('Godan', 'Munshi Premchand', 'Literature', 199.00, 20);
