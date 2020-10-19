create table if not exists users(
  id       serial,
  username varchar(256),
  password varchar(256)
);