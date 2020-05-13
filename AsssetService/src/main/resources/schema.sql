CREATE TABLE IF NOT EXISTS asset(
    asset_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    asset_name VARCHAR(50) NOT NULL,
    asset_description VARCHAR(100),
    asset_category VARCHAR(50) NOT NULL,
    availability VARCHAR(25) NOT NULL,
    allotted_to INTEGER
);
ALTER TABLE IF EXISTS asset ALTER COLUMN asset_id RESTART with 10000;