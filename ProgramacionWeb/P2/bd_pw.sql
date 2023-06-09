CREATE TABLE Usuarios (
  mail varchar(99) NOT NULL,
  password varchar(50) NOT NULL,
  name varchar(99) NOT NULL,
  username varchar(100) NOT NULL,
  rol varchar(10) NOT NULL,
  fechaRegistro datetime NOT NULL,
  fechaUltConex datetime NOT NULL,
  PRIMARY KEY (mail),
  CONSTRAINT UC_Usuario UNIQUE (mail,username)
);


CREATE TABLE Criticas (
  id int(6) NOT NULL,  
  puntuacion int(9) NOT NULL, 
  titulo varchar(99) NOT NULL,
  resena varchar(250) NOT NULL, 
  mail varchar(99) NOT NULL,
  like int(9) NOT NULL,
  dislike int(9) NOT NULL,
  idEsp int(9) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT UC_Critica UNIQUE (titulo, id),
  CONSTRAINT fk_Mail FOREIGN KEY (mail) REFERENCES Usuarios (mail)
);

CREATE TABLE VotantesCriticas (
  mail varchar(99) NOT NULL,
  id int(6) NOT NULL,
  voto varchar(30) NOT NULL, 
  PRIMARY KEY (mail),
  CONSTRAINT UC_VotanteCritica UNIQUE (mail),
  CONSTRAINT fk_MailVC FOREIGN KEY (mail) REFERENCES Usuarios (mail),
  CONSTRAINT fk_idVC FOREIGN KEY (id) REFERENCES Criticas (id)
);

CREATE TABLE EspPuntual (
  id int(9) NOT NULL,   
  titulo varchar(99) NOT NULL,
  descripcion varchar(999) NOT NULL, 
  categoria varchar(20) NOT NULL,
  localidades int(6) NOT NULL,
  localidadesVendidas int(6) NOT NULL, 
  fecha datetime NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT UC_Puntual UNIQUE (id)
);

CREATE TABLE  EspMultiple (
  id int(9) NOT NULL,  
  titulo varchar(99) NOT NULL, 
  descripcion varchar(999) NOT NULL,
  categoria varchar(20) NOT NULL,
  localidades int(6) NOT NULL,
  localidadesVendidas int(6) NOT NULL,  
  idFecha int(9) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT UC_Puntual UNIQUE (id),
  CONSTRAINT fk_idFEM FOREIGN KEY (idFecha) REFERENCES MultipleFechas (id)
);

CREATE TABLE  Fechas (
  id int(9) NOT NULL,
  fecha datetime NOT NULL, 
  PRIMARY KEY (fecha)
);

CREATE TABLE  MultipleFechas (
  id int(9) NOT NULL,
  idF int(9) NOT NULL, 
  PRIMARY KEY(id),
  CONSTRAINT fk_idMF FOREIGN KEY (id) REFERENCES EspMultiple (id),
  CONSTRAINT fk_fechasMFN FOREIGN KEY (idF) REFERENCES Fechas (id)
);

CREATE TABLE  EspTemporada (
  id int(9) NOT NULL,  
  titulo varchar(99) NOT NULL, 
  descripcion varchar(999) NOT NULL,
  categoria varchar(20) NOT NULL,
  localidades int(6) NOT NULL,
  localidadesVendidas int(6) NOT NULL, 
  idP int(9) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT UC_Temp UNIQUE (id),
  CONSTRAINT fk_idPET FOREIGN KEY (idP) REFERENCES TemporadaPase (id)
);

CREATE TABLE  Pases (
  id int(9) NOT NULL,
  fechaInicio datetime NOT NULL,
  diaSemana varchar(20) NOT NULL,
  fechaFinal datetime NOT NULL, 
  PRIMARY KEY (id)
);

CREATE TABLE  TemporadaPases (
  id int(9) NOT NULL,
  idP int(9) NOT NULL, 
  PRIMARY KEY(id, idP),
  CONSTRAINT fk_idTP FOREIGN KEY (id) REFERENCES EspTemporada (id),
  CONSTRAINT fk_paseTP FOREIGN KEY (idP) REFERENCES Pases (id)
);
