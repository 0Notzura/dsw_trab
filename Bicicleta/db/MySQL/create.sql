drop database if exists BICICLETA;

CREATE DATABASE BICICLETA;

USE BICICLETA;

CREATE TABLE CLIENTE(
ID BIGINT PRIMARY KEY auto_increment,
EMAIL VARCHAR(100) UNIQUE NOT NULL,
TELEFONE VARCHAR(15) NOT NULL,
SENHA VARCHAR(20),
SEXO CHAR(1),
CPF INT(11) UNIQUE NOT NULL,
NASCIMENTO DATE NOT NULL
);


CREATE TABLE LOCADORA(
ID BIGINT PRIMARY KEY auto_increment,
EMAIL VARCHAR(100) UNIQUE NOT NULL,
CNPJ VARCHAR(14) UNIQUE NOT NULL,
CIDADE VARCHAR(20),
SENHA VARCHAR(20),
NOME VARCHAR(30)
);

CREATE TABLE CADASTROS(
CADASTRO_ID BIGINT PRIMARY KEY auto_increment,
DIA VARCHAR(10),
HORA VARCHAR(5),
CLIENTE_ID BIGINT UNIQUE NOT NULL,
LOCADORA_ID BIGINT UNIQUE NOT NULL,
FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTE(ID),
FOREIGN KEY (LOCADORA_ID) REFERENCES LOCADORA(ID)
);

create table Usuario(
    id bigint PRIMARY KEY auto_increment, 
    nome varchar(256) not null, 
    login varchar(20) not null unique, 
    senha varchar(64) not null, 
    papel varchar(10)
    );


insert into CLIENTE(EMAIL,TELEFONE,SENHA,SEXO,CPF,NASCIMENTO) values ('abc@123.com.bc','19999776600','12334','M',4324123,12/03/2001)

insert into LOCADORA(EMAIL,CNPJ,CIDADE,SENHA,NOME) VALUES ('EMPRESA@GMAIL.COM','123456789012345','PIRACICABA','admin','caterpillar')

insert into CADASTROS(DIA,HORA,CLIENTE_ID,LOCADORA_ID) VALUES (12/02/2001,'13:00',1,1)

INSERT INTO USUARIO (NOME,LOGIN,SENHA,PAPEL) VALUES ('GIOVANI', 'ADMIN','ADMIN', 'ADMIN')
