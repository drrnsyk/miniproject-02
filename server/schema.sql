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