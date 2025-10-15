CREATE TABLE employee (
    id uuid NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(255),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
);