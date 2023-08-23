ALTER TABLE users ADD COLUMN phone_number VARCHAR(20);
ALTER TABLE users ADD COLUMN email VARCHAR(64);

CREATE TABLE manager (
    id           UUID          DEFAULT gen_random_uuid() PRIMARY KEY,
    username     VARCHAR(64)   NOT NULL UNIQUE,
    password     VARCHAR(2048) NOT NULL,
    role         VARCHAR(32)   NOT NULL,
    first_name   VARCHAR(64)   NOT NULL,
    last_name    VARCHAR(64)   NOT NULL,
    salary       DECIMAL,
    phone_number VARCHAR(20),
    email        VARCHAR(64),
    enabled      BOOLEAN       NOT NULL DEFAULT FALSE,
    created_at   TIMESTAMP     DEFAULT now(),
    updated_at   TIMESTAMP     DEFAULT now()
);

CREATE TABLE request (
    id         UUID          DEFAULT gen_random_uuid() PRIMARY KEY,
    title      VARCHAR(64),
    created_at TIMESTAMP     DEFAULT now(),
    updated_at TIMESTAMP     DEFAULT now()
);