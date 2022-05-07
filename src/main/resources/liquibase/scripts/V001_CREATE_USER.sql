CREATE TABLE "user" (
    id uuid NOT NULL UNIQUE,
    username VARCHAR(20) NOT NULL UNIQUE,
    surname VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);