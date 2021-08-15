CREATE TABLE IF NOT EXISTS balance(
    id INTEGER NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    balance DOUBLE,
    PRIMARY KEY(id)
);