drop table if exists items cascade ;
drop table if exists users cascade ;
CREATE TABLE IF NOT EXISTS users (
         id SERIAL PRIMARY KEY,
         username VARCHAR(50) NOT NULL,
         email VARCHAR(50) NOT NULL unique ,
         phone VARCHAR(30) NOT NULL
);
create table if not exists items (
         id SERIAL PRIMARY KEY,
         name TEXT,
         description TEXT,
         created  TIMESTAMP,
         done boolean,
         user_id int not null references users(id)
);

