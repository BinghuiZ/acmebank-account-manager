CREATE TABLE IF NOT EXISTS bankaccount(
    id INTEGER NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    balance decimal(15,2),
    PRIMARY KEY(id)
);