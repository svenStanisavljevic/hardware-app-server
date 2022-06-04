CREATE TABLE IF NOT EXISTS hardware (
    id LONG IDENTITY,
    code VARCHAR(20) UNIQUE,
    name VARCHAR(50),
    price FLOAT,
    type VARCHAR(20),
    instock INT,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS review (
    id LONG IDENTITY,
    title VARCHAR(50),
    text VARCHAR(1000),
    rating INT,
    hardware_id LONG,
    PRIMARY KEY (id),
    CONSTRAINT fk_hardware FOREIGN KEY (hardware_id) REFERENCES hardware(id)
);
CREATE TABLE IF NOT EXISTS user (
    id LONG IDENTITY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password varchar(1000) NOT NULL
);
CREATE TABLE IF NOT EXISTS authority (
    id LONG IDENTITY,
    authority_name VARCHAR(100) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS user_authority (
    user_id LONG NOT NULL,
    authority_id LONG NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_authority FOREIGN KEY (authority_id) REFERENCES authority(id)
);