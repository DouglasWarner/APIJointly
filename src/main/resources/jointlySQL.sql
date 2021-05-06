CREATE DATABASE IF NOT EXISTS Jointly;

CREATE TABLE IF NOT EXISTS user(
	id INTEGER NOT NULL AUTO_INCREMENT,
	email VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	name VARCHAR(255) NOT NULL,
	phone VARCHAR(20) NULL,
	imagen MEDIUMBLOB NULL,
	location VARCHAR(255) NULL,
	description VARCHAR(255) NULL,
	createAt DATETIME DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (id)
);

create table if not exists initiative (
	id INTEGER NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	createAt DATETIME DEFAULT CURRENT_TIMESTAMP,
	targetDate DATETIME NOT NULL,
	description VARCHAR(255) NULL,
	targetArea VARCHAR(255) NOT NULL,
	location VARCHAR(255) NOT NULL,
	imagen MEDIUMBLOB NULL,
	targetAmount INTEGER NOT NULL,
	status ENUM('A','D') DEFAULT 'A',
	createBy VARCHAR(255) NOT NULL,
	refCode VARCHAR(255) UNIQUE NOT NULL,
	
	PRIMARY KEY (id),
	
	FOREIGN KEY (createBy) REFERENCES user(email)
);

create table if not exists user_follow_user (
	user VARCHAR(255) NOT NULL,
	userFollow VARCHAR(255) NOT NULL,
	
	PRIMARY KEY (user, userFollow),
	
	FOREIGN KEY (user) REFERENCES user(email),
	FOREIGN KEY (userFollow) REFERENCES user(email)
);

create table if not exists user_review_user (
	date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	user VARCHAR(255) NOT NULL,
	userReview VARCHAR(255) NOT NULL,
	review VARCHAR(255) NULL,
	stars TINYINT NOT NULL,
	
	PRIMARY KEY (date, user, userReview),
	
	FOREIGN KEY (user) REFERENCES user(email),
	FOREIGN KEY (userReview) REFERENCES user(email)
);

create table if not exists user_join_initiative (
	date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	user VARCHAR(255) NOT NULL,
	initiative INTEGER NOT NULL,
	type BIT DEFAULT 0 NOT NULL,
	
	PRIMARY KEY (date, user, initiative),
	
	FOREIGN KEY (user) REFERENCES user(email),
	FOREIGN KEY (initiative) REFERENCES initiative(id)
);

INSERT INTO user (email, password, name, phone, imagen, location, description) 
VALUES ('douglas@gmail.com', '12345678', 'douglas', '666000222', null, 'malaga', 'description');

INSERT INTO user (email, password, name, phone, imagen, location, description) 
VALUES ('email@gmail.com', '12345678', 'name', 'phone', null, 'location', 'description');

INSERT INTO user_follow_user VALUES ('email@gmail.com', 'douglas@gmail.com');

INSERT INTO initiative(name, targetDate, description, targetArea, location, imagen, targetAmount, status, createBy, refCode)
VALUES ('iniciativa 3', '2021-04-23', 'descripcion 3', 'Recolecta', 'location', null, '30', 'A', 'douglas@gmail.com', '145');

INSERT INTO initiative(name, targetDate, description, targetArea, location, imagen, targetAmount, status, createBy, refCode)
VALUES ('iniciativa 4', '2021-04-23', 'descripcion 4', 'Recolecta', 'location', null, '30', 'A', 'douglas@gmail.com', '145');

INSERT INTO user_join_initiative(idInitiative, idUser, type) 
VALUES (2,'email@gmail.com', 0);

INSERT INTO user_review_user (user, userReview, review, stars)
VALUES ('douglas@gmail.com', 'email@gmail.com', null, 3);


UPDATE user_join_initiative SET type=1 WHERE date='2021-05-04 23:48:51' AND idInitiative=2 AND idUser='douglas@gmail.com';

UPDATE initiative SET name='ini 1', targetDate='2021-04-21', description='desc 2', targetArea='Saneamiento', location='Sevilla', imagen=null, targetAmount=2, status='D' where id=2;
