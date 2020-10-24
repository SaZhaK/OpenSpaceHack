create table if not exists users(
  id                             serial,
  username                       varchar(256),
  password                       varchar(256),
  role                           varchar(256),
  first_name                     varchar(256),
  second_name                    varchar(256),
  last_name                      varchar(256)
);

create table if not exists user_to_bugs(
  id      serial;
  user_id integer;
  bug_id  integer;
);

create table if not exists bugs(
  id          serial,
  description varchar,
  screenshot  blob, -- lo?
  status      integer -- 0 - обработано; 1 - необработано
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

create table if not exists jackets(
  id    serial,
  image blob --lo
);

create table if not exists images(
  id serial,
  image varchar
);