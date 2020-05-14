CREATE TABLE IF NOT EXISTS users(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(40) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
ALTER TABLE users ALTER COLUMN id RESTART WITH 10000;