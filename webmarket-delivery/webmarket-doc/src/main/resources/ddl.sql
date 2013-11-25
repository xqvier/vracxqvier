drop table product;
create table product (id INT generated always as identity, name VARCHAR(200), price DECIMAL(11,2));
INSERT INTO PRODUCT (name, price) values ('Banane', 12.3); 
INSERT INTO PRODUCT (name, price) values ('Poire', 5.35); 
INSERT INTO PRODUCT (name, price) values ('Tomate', 15.12); 
