CREATE TABLE balance (
    user_id uuid NOT NULL UNIQUE,
    current_value NUMERIC(10,2) NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);