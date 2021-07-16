CREATE TABLE customer (
    id serial PRIMARY KEY,
    name varchar(100),
    last_name varchar(100),
    email varchar(100),
    gender varchar(20),
    phone_number varchar(20),
    date_of_birth date
);
