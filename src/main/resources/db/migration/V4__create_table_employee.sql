CREATE TABLE employee (
    id uuid NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(255)
);