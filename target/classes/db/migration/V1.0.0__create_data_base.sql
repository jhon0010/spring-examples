CREATE TABLE IF NOT EXISTS customer (
    id serial PRIMARY KEY,
    name varchar(100),
    last_name varchar(100),
    email varchar(100),
    gender varchar(20),
    phone_number varchar(20),
    date_of_birth date
);



INSERT INTO public.customer(name, last_name, email, gender, phone_number, date_of_birth)
VALUES('Jhon', 'Gomez', 'jhon.lotero@gmail.com', 'MALE', '3001112233', '1990-01-26');

INSERT INTO public.customer(name, last_name, email, gender, phone_number, date_of_birth)
VALUES('Estefania', 'Perez', 'Estefania.perez@gmail.com', 'FEMALE', '3040002233', '1993-08-03');
