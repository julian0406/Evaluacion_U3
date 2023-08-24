create database  recupera2;
use recupera2;

create table usuarios(
	idusuario int primary key,
    name varchar(255) unique,
    password varchar(255),
    rol varchar(255)
);

insert into usuarios values(1, "julian", "julian123", "USER_ROLE");
insert into usuarios values(2, "erik", "erik123", "USER_ROLE");
insert into usuarios values(3, "david", "david123", "CHARGE_ROLE");
insert into usuarios values(4, "miguel", "miguel123", "ADMIN_ROLE");

create table incidencias(
idincidencia int primary key auto_increment,
titulo varchar(255),
descripcion varchar(255),
tipo varchar(255),
estado varchar(255) default 'Pendiente',
usuario int references usuarios(idusuario)
);
