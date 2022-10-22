CREATE TABLE customer(
	id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(255),
    email varchar(255) UNIQUE
);