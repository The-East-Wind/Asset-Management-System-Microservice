CREATE TABLE IF NOT EXISTS request(
    request_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    requested_from DATE NOT NULL,
    requested_till DATE NOT NULL,
    requested_by INTEGER NOT NULL,
    requested_for INTEGER NOT NULL,
    requested_asset INTEGER NOT NULL,
    status VARCHAR(30) NOT NULL
);
ALTER TABLE request ALTER COLUMN request_id RESTART WITH 1;