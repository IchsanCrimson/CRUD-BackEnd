# Training CRUD

Backend run on: http://localhost:8081/

Swagger documentation: http://localhost:8081/swagger-ui.html

Frontend Repository: https://github.com/IchsanCrimson/CRUD-FrontEnd

![image](https://user-images.githubusercontent.com/87750410/226195200-db55a39a-1c12-47bc-a591-cefa5d1debcf.png)

# Database PostgreSQL

Query to create database:

```

CREATE DATABASE testing;

\c testing;


CREATE TABLE provinsi (
	provinsi_id serial PRIMARY KEY,
	name VARCHAR UNIQUE
);

CREATE TABLE kabupaten (
	kabupaten_id serial PRIMARY KEY,
	provinsi_id int NOT NULL,
	name VARCHAR UNIQUE,
	FOREIGN KEY (provinsi_id) REFERENCES provinsi(provinsi_id)
);


CREATE TABLE kecamatan (
	kecamatan_id serial PRIMARY KEY,
	kabupaten_id int NOT NULL,
	name VARCHAR UNIQUE,
	FOREIGN KEY (kabupaten_id) REFERENCES kabupaten(kabupaten_id)
);

```
