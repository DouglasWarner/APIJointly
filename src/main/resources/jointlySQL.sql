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
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY (id)
);

create table if not exists initiative (
	id INTEGER NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
	target_date DATETIME NOT NULL,
	description VARCHAR(255) NULL,
	target_area VARCHAR(255) NOT NULL,
	location VARCHAR(255) NOT NULL,
	imagen MEDIUMBLOB NULL,
	target_amount INTEGER NOT NULL,
	status ENUM('A','D') DEFAULT 'A',
	created_by VARCHAR(255) NOT NULL,
	ref_code VARCHAR(255) UNIQUE NOT NULL,
	
	PRIMARY KEY (id),
	
	FOREIGN KEY (created_by) REFERENCES user(email)
);

create table if not exists user_follow_user (
	user VARCHAR(255) NOT NULL,
	user_follow VARCHAR(255) NOT NULL,
	
	PRIMARY KEY (user, user_follow),
	
	FOREIGN KEY (user) REFERENCES user(email),
	FOREIGN KEY (user_follow) REFERENCES user(email)
);

create table if not exists user_review_user (
	date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	user VARCHAR(255) NOT NULL,
	user_review VARCHAR(255) NOT NULL,
	review VARCHAR(255) NULL,
	stars TINYINT NOT NULL,
	
	PRIMARY KEY (date, user, user_review),
	
	FOREIGN KEY (user) REFERENCES user(email),
	FOREIGN KEY (user_review) REFERENCES user(email)
);

create table if not exists user_join_initiative (
	date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	user_email VARCHAR(255) NOT NULL,
	id_initiative INTEGER NOT NULL,
	type BIT DEFAULT 0 NOT NULL,
	
	PRIMARY KEY (date, user, initiative),
	
	FOREIGN KEY (user_email) REFERENCES user(email),
	FOREIGN KEY (id_initiative) REFERENCES initiative(id)
);

INSERT INTO user (email, password, name, phone, imagen, location, description) 
VALUES ('douglas@gmail.com', '12345678', 'douglas', '666000222', null, 'malaga', 'description');

INSERT INTO user (email, password, name, phone, imagen, location, description) 
VALUES ('email@gmail.com', '12345678', 'name', 'phone', null, 'location', 'description');

INSERT INTO user_follow_user VALUES ('email@gmail.com', 'douglas@gmail.com');

INSERT INTO initiative(name, target_date, description, target_area, location, imagen, target_amount, status, created_by, ref_code)
VALUES ('iniciativa 3', '2021-04-23', 'descripcion 3', 'Recolecta', 'location', null, '30', 'A', 'douglas@gmail.com', '145');

INSERT INTO initiative(name, target_date, description, target_area, location, imagen, target_amount, status, created_by, ref_code)
VALUES ('iniciativa 4', '2021-04-23', 'descripcion 4', 'Recolecta', 'location', null, '30', 'A', 'douglas@gmail.com', '145');

INSERT INTO user_join_initiative(id_initiative, user_email, type) 
VALUES (2,'email@gmail.com', 0);

INSERT INTO user_review_user (user, user_review, review, stars)
VALUES ('douglas@gmail.com', 'email@gmail.com', null, 3);


UPDATE user_join_initiative SET type=1 WHERE date='2021-05-04 23:48:51' AND id_initiative=2 AND user_email='douglas@gmail.com';

UPDATE initiative SET name='ini 1', target_date='2021-04-21', description='desc 2', target_area='Saneamiento', location='Sevilla', imagen=null, target_amount=2, status='D' where id=2;
