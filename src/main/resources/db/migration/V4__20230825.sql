CREATE TABLE note (
    id         UUID          DEFAULT gen_random_uuid() PRIMARY KEY,
    topic      VARCHAR(64)   NOT NULL,
    title      VARCHAR(64)   NOT NULL,
    text       VARCHAR       NOT NULL,
    created_at TIMESTAMP     DEFAULT now(),
    updated_at TIMESTAMP     DEFAULT now()
);