
--
-- CREATION DES RELATIONS DU SCHEMA NATURA
--
-- Suppression des relations dans le cas où elles existeraient
-

ALTER TABLE plante DROP CONSTRAINT pk_plante ;
ALTER TABLE plante DROP CONSTRAINT ck_dateflor ;

ALTER TABLE zonepeup DROP CONSTRAINT pk_zonepeup ;
ALTER TABLE zonepeup DROP CONSTRAINT ck_altitude ;

ALTER TABLE agent DROP CONSTRAINT pk_agent ;

ALTER TABLE prescripteur DROP CONSTRAINT pk_prescripteur ;

ALTER TABLE recensement DROP CONSTRAINT pk_recensement ;
ALTER TABLE recensement DROP CONSTRAINT fk_recens_presc ;
ALTER TABLE recensement DROP CONSTRAINT fk_recens_agent ;

ALTER TABLE releve DROP CONSTRAINT pk_releve ;
ALTER TABLE releve DROP CONSTRAINT fk_releve_agent	;
ALTER TABLE releve DROP CONSTRAINT fk_releve_recens	;
ALTER TABLE releve DROP CONSTRAINT fk_releve_plante	;
ALTER TABLE releve DROP CONSTRAINT fk_releve_zone ;
ALTER TABLE releve DROP CONSTRAINT ck_etatfleur	;
ALTER TABLE releve DROP CONSTRAINT ck_posquantm ;
ALTER TABLE releve DROP CONSTRAINT ck_postaillem ;
 
DROP TABLE agent ;
DROP TABLE prescripteur ;
DROP TABLE plante  ;
DROP TABLE zonepeup ;
DROP TABLE releve ;
DROP TABLE recensement ;


CREATE TABLE plante 
	(
	nomscient	VARCHAR(30), 
	nomcommun	VARCHAR(35)	NOT NULL, 
	couleurfleur	VARCHAR(18), 
	dateflormin	DATE, 
	dateflormax	DATE, 
	espece		VARCHAR(30)	NOT NULL,
	descriptif	LONG,
	CONSTRAINT pk_plante PRIMARY KEY (nomscient),
	CONSTRAINT ck_dateflor CHECK (dateflormax >= dateflormin) 
	) ;


CREATE TABLE zonepeup 
	(
	cadastre	CHAR(14)	NOT NULL, 
	lieudit		VARCHAR(40), 
	commune		VARCHAR(30)	NOT NULL, 
	departement	VARCHAR(20)	NOT NULL,
	sol			VARCHAR(20),
	inclinaison	VARCHAR(20),
	typearbre	VARCHAR(20),
	altmin		NUMERIC(4),
	altmax		NUMERIC(4),
	CONSTRAINT pk_zonepeup PRIMARY KEY (cadastre),
	CONSTRAINT ck_altitude CHECK (altmax >= altmin)
	) ;

CREATE TABLE agent 
	(
	matricule	CHAR(5), 
	nom			VARCHAR(25)	NOT NULL, 
	prenom		VARCHAR(25), 
	rue			VARCHAR(50)	NOT NULL,
	codepost	CHAR(5)		NOT NULL,
	ville		VARCHAR(50)	NOT NULL,
	mel			VARCHAR(28),
	tel			CHAR(10),
	CONSTRAINT pk_agent PRIMARY KEY (matricule)
	) ;

CREATE TABLE prescripteur
	(
	numadmin	CHAR(5), 
	organisme	VARCHAR(25)	NOT NULL, 
	service		VARCHAR(25), 
	rue			VARCHAR(50)	NOT NULL,
	codepost	CHAR(5)		NOT NULL,
	ville		VARCHAR(50)	NOT NULL,
	mel			VARCHAR(28),
	tel			CHAR(10),
	CONSTRAINT pk_prescripteur PRIMARY KEY (numadmin)
	) ;

CREATE TABLE recensement 
	(
	numero		CHAR(11)	NOT NULL, 
	datedem		DATE		NOT NULL, 
	daterecens	DATE		NOT NULL, 
	codepresc	CHAR(5)		NOT NULL,
	coderesp	CHAR(5)		NOT NULL,
	CONSTRAINT pk_recensement PRIMARY KEY (numero),
	CONSTRAINT fk_recens_presc FOREIGN KEY (codepresc)	REFERENCES prescripteur(numadmin),
	CONSTRAINT fk_recens_agent FOREIGN KEY (coderesp)	REFERENCES agent(matricule)
	) ;

CREATE TABLE releve 
	(
	lapers		CHAR(5),
	lerec		CHAR(11),
	laplante	VARCHAR(30),
	lazone		CHAR(14),
	quantite	NUMERIC(3),
	etatm		VARCHAR(9),
	taillem		NUMERIC(4),
	CONSTRAINT pk_releve PRIMARY KEY (lapers, lerec, laplante, lazone),
	CONSTRAINT fk_releve_agent	FOREIGN KEY (lapers)   REFERENCES agent(matricule),
	CONSTRAINT fk_releve_recens	FOREIGN KEY (lerec)    REFERENCES recensement(numero),
	CONSTRAINT fk_releve_plante	FOREIGN KEY (laplante) REFERENCES plante(nomscient),
	CONSTRAINT fk_releve_zone	FOREIGN KEY (lazone)   REFERENCES zonepeup(cadastre),
	CONSTRAINT ck_etatfleur		CHECK (etatm IN ('pousse','adulte','floraison','desséché')),
	CONSTRAINT ck_posquantm		CHECK (quantite >= 1),
	CONSTRAINT ck_postaillem	CHECK (taillem > 0)
	) ;
 