-- Skript for å opprette databasen og legge inn litt data
-- Skjema = hello_jpa
-- Tabell(er) = ansatt

-- MERK!!! DROP SCHEMA ... CASCADE sletter alt !!!
DROP SCHEMA IF EXISTS oblig3 CASCADE;

CREATE SCHEMA oblig3;
SET search_path TO oblig3;


DROP TABLE IF EXISTS ansatt;
DROP TABLE IF EXISTS avdeling;

-- Ikke nødvendig å slette tabellen(e) siden vi har tomt skjema, men ...
-- DROP TABLE ansatt;
CREATE TABLE avdeling
(
    avdid SERIAL,
    navn  VARCHAR(32),
    sjef  SMALLINT,

    UNIQUE (avdid),

    CONSTRAINT Avdeling_PK PRIMARY KEY (avdid)

);


CREATE TABLE ansatt
(
    id         SERIAL,
    brukernavn VARCHAR(30) NOT NULL,
    fornavn    VARCHAR(30) NOT NULL,
    etternavn  VARCHAR(30) NOT NULL,
    datoans    DATE,
    stilling   VARCHAR(30) NOT NULL,
    mndlon     integer     NOT NULL,
    avdeling_id small int NOT NULL,
    UNIQUE (brukernavn),
	CONSTRAINT ansatt_pk PRIMARY KEY (id)
	foreign key (avdeling_id) references avdeling (avdid)
);



CREATE TABLE Prosjekt (
	PrId SERIAL,
	Navn VARCHAR(32),
	Beskrivelse VARCHAR(255),
	Ansatte INTEGER,

	UNIQUE (PrId),

	CONSTRAINT Prosjekt_PK PRIMARY KEY (PrId)
);


CREATE TABLE AnsattProsjekt (
	AnsId INTEGER REFERENCES Ansatt(Id),
	PrId INTEGER REFERENCES Prosjekt(PrId),
	Timer INTEGER,
	Rolle VARCHAR(32),

	CONSTRAINT AnsattProsjektPK PRIMARY KEY (Id, PrId)
);

-- Legger til fremmednøkler


ALTER TABLE avdeling
    ADD FOREIGN KEY (sjef) REFERENCES ansatt (id);
    
insert into avdeling (navn)
values ('Litt penger),
	   ('Meir penger),
	   ('Mest penger),    
    

INSERT INTO ansatt(brukernavn, fornavn, etternavn, datoans,stilling, mndlon, avdeling_id)
VALUES ('skrm', 'Skrue', 'McDuck', '1900-01-01', 'sjef', '100000', '3'),
       ('mikm', 'Mike', 'Mus', '1940-01-01', 'tulling', '1000', '2'),
       ('dond', 'Donald', 'Duck', '1920-01-01', 'tulling', '100', '1'),
       ('minm', 'Minni', 'Mus', '1980-01-01', 'tulling', '90', '2'),
       ('oled', 'Ole', 'Duck', '2000-01-01', 'tulling', '50', '1'),
       ('dold', 'Dole', 'Duck', '2000-01-01', 'tulling', '50', '1'),
       ('dofd', 'Doffen', 'Duck', '2000-01-01', 'tulling', '50', '1');
       
insert into Prosjekt(navn, beskrivelse)
values ('Tjene Skrue penger', 'Dette prosjektet skal tene penger til skrue')
	   ('Skattesnylting', 'Dette prosjekter skal hindre Skrue i og skatte')
	   
	   
insert into AnsattProsjekt(AnsId, PrId, Timer, Rolle)
values (2, 2, 20, 'Bestikker'),
	   (6, 1, 70, 'Gruvearbeider'),
	   (1, 1, 100, 'Prosjektleder'),
	   (3, 2, 40, 'Spesieltype') 	   

update avdeling
set sjef_id = 3
where id = 1;
update avdeling 
set sjef_id = 3
where id = 2
update avdeling
set sjef_id=1
where id = 3;	   
	   
