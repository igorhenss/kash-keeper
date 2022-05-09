CREATE TABLE balance_history (
    id uuid NOT NULL UNIQUE,
    balance_id uuid NOT NULL,
    title VARCHAR(40) NOT NULL,
    description VARCHAR(512),
    added_value NUMERIC(10,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    FOREIGN KEY (balance_id) REFERENCES "balance" (user_id)
);