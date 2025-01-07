-- create db
create database if not exists rsvp;

-- select db
use rsvp;

-- create rsvp table
select "Creating rsvp table..." as msg;
create table rsvp (
    id int auto_increment primary key,
    name varchar(255) not null,
    email varchar(255) unique not null,
    phone varchar(8) not null,
    confirmDate date not null,
    comments text
);

-- grant access to fred
select "Granting access to fred" as msg;
grant all privileges on rsvp.* to 'fred'@'%';
flush privileges;