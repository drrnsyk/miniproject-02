# MySQL Database Initial Setup

CREATE DATABASE miniproject02;

USE miniproject02;

CREATE USER 'darren'@'localhost' IDENTIFIED BY 'Password@123456';
GRANT ALL PRIVILEGES ON *.* TO 'darren'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

create table users (
	id int auto_increment not null,
	email varchar(255),
	name varchar(255),
	password varchar(255),
	role varchar(255),
	primary key (id)
);

CREATE TABLE stores (
  storeID VARCHAR(255) NOT NULL,
  storeName VARCHAR(255),
  isActive VARCHAR(10),
  imagesBanner VARCHAR(255),
  imagesLogo VARCHAR(255),
  imagesIcon VARCHAR(255),
  PRIMARY KEY (storeID)
);

insert into users (id, email, name, password, role) values ('1', 'admin@gmail.com', 'Administrator',  '$2a$12$122ZD9ZaPutMwU4XxT/.VOglGwSz6YzYVcw/gAmyRdQ7xLciCjThW', 'ADMIN');