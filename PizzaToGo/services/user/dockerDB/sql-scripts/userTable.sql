CREATE TABLE user (
	user_id int auto_increment primary key,
	username varchar(20) not null UNIQUE,
	E_Mail varchar(50) not null,
	firstname varchar(20) not null,
	lastname varchar(20) not null,
	street   varchar(30) not null,
	streetNr  varchar(20) not null,
	zip  varchar(20) not null,
	city  varchar(30) not null,
	password_hash VARBINARY (1000) not null,
	password_salt VARBINARY (1000) not null
)CHARACTER SET utf8mb4;