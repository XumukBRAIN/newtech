ALTER TABLE request ADD COLUMN status INTEGER NOT NULL;
ALTER TABLE request ADD COLUMN number VARCHAR NOT NULL;
CREATE SEQUENCE request_number_seq start WITH 1;