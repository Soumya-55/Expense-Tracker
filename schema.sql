--CREATE DATABASE expense_tracker;
--
--USE expense_tracker;
--
--CREATE TABLE users (
--    user_id INT AUTO_INCREMENT PRIMARY KEY,
--    username VARCHAR(50) NOT NULL,
--    password VARCHAR(100) NOT NULL,
--    email VARCHAR(100) NOT NULL
--);
--
--CREATE TABLE expenses (
--    expense_id INT AUTO_INCREMENT PRIMARY KEY,
--    user_id INT,
--    amount DECIMAL(10, 2),
--    category VARCHAR(50),
--    date DATE,
--    description TEXT,
--    FOREIGN KEY (user_id) REFERENCES users(user_id)
--);
CREATE DATABASE IF NOT EXISTS expense_tracker;

USE expense_tracker;

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

-- Create expenses table
CREATE TABLE IF NOT EXISTS expenses (
    expense_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    amount DECIMAL(10, 2),
    category VARCHAR(50),
    date DATE,
    description TEXT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
