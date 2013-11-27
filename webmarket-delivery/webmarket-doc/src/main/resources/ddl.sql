drop table product;
create table product (id INT generated always as identity, name VARCHAR(200), price DECIMAL(11,2));
INSERT INTO PRODUCT (name, price) values ('Banane', 12.3); 
INSERT INTO PRODUCT (name, price) values ('Poire', 5.35); 
INSERT INTO PRODUCT (name, price) values ('Tomate', 15.12); 


DROP TABLE client;

CREATE TABLE client (id INT generated always AS identity, username VARCHAR(50), password VARCHAR(50), firstname VARCHAR(50), lastname VARCHAR(50));
INSERT INTO client (username, password, firstname, lastname) VALUES('xqvier', 'toto', 'Xavier', 'Mourgues')

