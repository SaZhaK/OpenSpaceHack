create table if not exists users(
  id                             serial,
  username                       varchar(256),
  password                       varchar(256),
  role                           varchar(256),
  first_name                     varchar(256),
  second_name                    varchar(256),
  last_name                      varchar(256),
  money                          integer
);

create table if not exists user_to_bugs(
  id      serial;
  user_id integer;
  bug_id  integer;
);

create table if not exists bugs(
  id           serial,
  bugName      varchar(256),
  description  varchar,
  testedSystem varchar,
  betaVersion  varchar(256),
  OSModel      varchar(512),
  screenshot   blob, -- lo?
  date         date,
  time         time,
  status       integer -- 0 - обработано; 1 - необработано
);

create table if not exists comments(
  id      serial,
  user_id integer,
  bug_id  integer,
  text    varchar,
);

create table if not exists pets(
  id     serial,
  owner  integer,
  name   varchar(256),
  rank   integer,
  face integer,
  body integer,
  leg integer,
  hat integer,
  backpack integer
);

create table if not exists user_to_items(
  id serial,
  user_id integer,
  item_id integer
);

create table if not exists items(
  id serial,
  type varchar(256),
  link varchar(256),
  cost integer
);

/*
create table if not exists hats(
  id    serial,
  wear  int, -- 0 - false; 1 - true
  image blob --lo
);

create table if not exists faces(
  id    serial,
  wear  int, -- 0 - false; 1 - true
  image blob --lo
);

create table if not exists mouths(
  id    serial,
  wear  int,
  image blob
);

create table if not exists backpacks(
  id    serial,
  wear  int, -- 0 - false; 1 - true
  image blob --lo
);

create table if not exists bodies(
    id    serial,
    wear  int, -- 0 - false; 1 - true
    image blob --lo
);

create table if not exists legs(
    id    serial,
    wear  int, -- 0 - false; 1 - true
    image blob --lo
);

create table if not exists images(
  id serial,
  wear  int, -- 0 - false; 1 - true
  image varchar
);*/
