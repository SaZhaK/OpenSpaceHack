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

create table if not exists messages(
  id      serial,
  user_id integer,
  text    varchar,
  time    timestamp
);

create table if not exists pets(
  id     serial,
  owner  integer,
  name   varchar(256),
  rank   integer,
  hat    integer,
  jacket integer
);

create table if not exists hats(
  id    serial,
  image blob --lo
);

create table if not exists heads(
  id    serial,
  image blob --lo
);

create table if not exists faces(
  id    serial,
  image blob --lo
);

create table if not exists arms(
  id    serial,
  image blob --lo
);

create table if not exists backpacks(
  id    serial,
  image blob --lo
);

create table if not exists bodies(
    id    serial,
    image blob --lo
);

create table if not exists legs(
    id    serial,
    image blob --lo
);

create table if not exists skin(
    id    serial,
    image blob --lo
);

create table if not exists stickers(
    id    serial,
    image blob --lo
);

create table if not exists backgrounds(
    id    serial,
    image blob --lo
);

create table if not exists images(
  id serial,
  image varchar
);