create table IF NOT EXISTS addresses(id int auto_increment primary key,street varchar(255),number int,location varchar (255),province varchar (255));
create table IF NOT EXISTS patients(id int auto_increment primary key,name varchar(255),lastname varchar (255),email varchar (255), dni varchar (255),addmission_date TIMESTAMP WITHOUT TIME ZONE,address_id int);
create table IF NOT EXISTS dentists(id int auto_increment primary key,name varchar(255),lastname varchar (255),email varchar (255), matricula varchar (255),dni varchar(255),address_id int);
create table IF NOT EXISTS appointments(id int auto_increment primary key,patient_id int, dentist_id int, consulting_date TIMESTAMP WITHOUT TIME ZONE, consulting_room int);