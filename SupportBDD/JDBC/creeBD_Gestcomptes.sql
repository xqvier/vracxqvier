
ALTER TABLE histotrans DROP CONSTRAINT pk_histotrans ;
ALTER TABLE histotrans DROP CONSTRAINT fk_histocomptedeb ;
ALTER TABLE histotrans DROP CONSTRAINT fk_histocomptecred ;

ALTER TABLE archivecompte DROP CONSTRAINT pk_arccompte	;
ALTER TABLE arccompte DROP CONSTRAINT pk_arccompte	;

ALTER TABLE compte DROP CONSTRAINT pk_compte	;
ALTER TABLE compte DROP CONSTRAINT fk_numcli 	;
ALTER TABLE compte DROP CONSTRAINT ck_statut 	;

ALTER TABLE client DROP CONSTRAINT pk_client	;


DROP TABLE histotrans;
DROP TABLE archivecompte;
DROP TABLE compte ;
DROP TABLE client ;
 

CREATE TABLE client
-- Le client existe ind�pendamment du compte bancaire.
(
	numcli	CHAR(6)		CONSTRAINT pk_client	PRIMARY KEY,
	nom 	VARCHAR(20)	NOT NULL,
	prenom	VARCHAR(20)	NOT NULL,
	rue 	VARCHAR(40),
	ville	VARCHAR(20),
	cp 	CHAR(5)     ) ;

CREATE TABLE compte
-- Le compte poss�de un statut : A actif, C clotur�, archiv�
-- Lors de son archivage, on va stocker des informations dans archivecompte et dans compte, le statut est mis � 'C' 
-- en attendant qu'un traitement par lots supprime le compte quand il n'y aura plus de transactions associ�es.
(
	numcpt 		CHAR(5) 	CONSTRAINT pk_compte	PRIMARY KEY,
	statut		CHAR(1)		NOT NULL,
	solde  		NUMERIC(8,2)	NOT NULL,
	date_ouv   	DATE		NOT NULL,
	numcli		CHAR(6) 	NOT NULL,
	CONSTRAINT fk_numcli 		FOREIGN KEY (numcli) 	REFERENCES client(numcli),
	CONSTRAINT ck_statut 		CHECK (statut IN ('A','C'))        ) ;
	
CREATE TABLE archivecompte
-- Plus de cl� �trang�re vers client ou compte car les informations vont pouvoir �tre supprim�es
-- on m�morise toutefois des informations relatives � l'ancien possesseur de ce compte. 
(
	numcpt		CHAR(5) 	CONSTRAINT pk_arccompte	PRIMARY KEY,
	date_ouv   	DATE,
	date_clot 	DATE,
	numcli		CHAR(6),
	nom		VARCHAR(20) 	NOT NULL,
	prenom		VARCHAR(20)           ) ;

CREATE TABLE histotrans
-- Tout mouvement bancaire (d�pot, retrait, virement) est historis�
-- "numtrans" repr�sente le num�ro de la transaction
-- "cptdeb" mat�rialise le compte dont on d�bite de l�argent
-- "cptcred" mat�rialise le compte que l�on cr�dite
-- "ordo" repr�sentant la connexion � la base de donn�es : guichetier, automate, web
-- cette information est fournie par la connexion �tablie avec la base de donn�es.
(
 numtrans   	NUMERIC(8) 	CONSTRAINT pk_histotrans 	PRIMARY KEY,
 cptdeb		CHAR(5)		CONSTRAINT fk_histocomptedeb 	REFERENCES compte(numcpt),
 cptcred	CHAR(5)		CONSTRAINT fk_histocomptecred 	REFERENCES compte(numcpt),
 montant 	NUMERIC(8,2) 	NOT NULL,
 datetrans	DATE		NOT NULL,
 ordo 		CHAR(10) 	NOT NULL       ) ;

-- Cr�ation d'une s�quence : pour la num�rotation des transactions

CREATE SEQUENCE seq_transac ;



-- Ajout de donn�es

INSERT INTO client 
VALUES ('000001','FRAYSSE','Marcel','3 rue du bois','Rodez',12000);

INSERT INTO client
VALUES ('000002','MARCENAC','Sylvie','51 rue de la neige','Laguiole',12210);

COMMIT ;
