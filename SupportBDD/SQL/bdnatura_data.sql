
--Suppression des données si elles existent

DELETE FROM releve ;
DELETE FROM recensement ;
DELETE FROM prescripteur ;
DELETE FROM agent ;
DELETE FROM zonepeup ;
DELETE FROM plante ;


-- Création d'un jeu de données pour le test des requêtes

INSERT INTO plante VALUES ('lamium album','ortie blanche','blanche',TO_DATE('03/20/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'lamiacée', 'Plante semblable à l''ortie commune sans poils urticants. Tige creuse et quadrangulaire. Feuilles à dents pointues, pétiolées et opposées en coin. On la rencontre dans les chemins, les décombres, rarement dans les champs') ;
INSERT INTO plante VALUES ('orchidea youpiya','orchidée des prés','blanche',TO_DATE('04/15/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE('06/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),'orchidée', NULL) ;
INSERT INTO plante VALUES ('orchidea flagada','orchidée du causse noir','rouge',TO_DATE('04/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE('06/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),'orchidée', NULL) ;
INSERT INTO plante VALUES ('rosa rosae rosam','rosier grimpant','rose',TO_DATE('04/15/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE('08/15/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),'rosacée', NULL) ;
INSERT INTO plante VALUES ('glechoma hederacea','lierre terrestre','mauve',TO_DATE('03/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE('04/15/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'lamiacée', NULL) ;
INSERT INTO plante VALUES ('bisutella laevigata','lunetiaire lisse','jaune',TO_DATE('05/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE('11/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'brassicacée', 'grappe plus ou moins serrée. Fruit et ovaire en deux lobes ronds en forme de lunettes. Fruit étalé et dressé. Feuilles radicales allongées, sinuées dentées ou plus ou moins lobées') ;

INSERT INTO zonepeup VALUES ('12_RDZ_lot2189','Le fort','Aubin', 'Aveyron', 'argileux','pente forte','sapin', 240, 298) ;
INSERT INTO zonepeup VALUES ('12_RDZ_lot2190','La soulière','Aubin', 'Aveyron', 'silico-argileux','vallée','peuplier', 225, 247) ;
INSERT INTO zonepeup VALUES ('12_MIL_lot0040','La Malaurie','Creissels', 'Aveyron', 'argileux','falaise','pas d''arbre', 225, 675) ;
INSERT INTO zonepeup VALUES ('46_GRM_lot1156','Le Causse troué','Gramat','Lot', 'calcaire','plateau','chêne vert', 189, 207) ;
INSERT INTO zonepeup VALUES ('46_FGC_lot1251','Le Puech de l''aiguille','Figeac','Lot', 'calcaire','colline','sapin', 200, 247) ;
INSERT INTO zonepeup VALUES ('12_CPD_lot9875','Planèze','Capdenac', 'Aveyron', 'sablonneux','vallée','pas d''arbre', 180, 193) ;
INSERT INTO zonepeup VALUES ('12_CPD_lot1005', NULL,'Villeneuve d''Aveyron', 'Aveyron', 'calcaire','plateau','sapin', 284, 307) ;

INSERT INTO agent VALUES ('0C850', 'Pradels', 'Julia', '3 rue de la colline', '48000', 'Mende','jpradels@natura.com','0466212212');
INSERT INTO agent VALUES ('0F570', 'Malirat', 'Pierre', '6 avenue des Pyrénées', '65300', 'Lannemezan','pmalirat@natura.com','0562555121');
INSERT INTO agent VALUES ('0B190', 'Pradels', 'Marc', '7 avenue des vignobles', '81800', 'Rabastens','mpradels@natura.com','0565339999');
INSERT INTO agent VALUES ('0C151', 'Esteban', 'Pablo', '3 boulevard Magenta', '31000', 'Toulouse','pesteban@natura.com','0561001200');
INSERT INTO agent VALUES ('0C221', 'Dupre', 'Noémie', '184 côte du Larzac', '12100', 'Millau','dudup@larzac.net',NULL);
INSERT INTO agent VALUES ('0F291', 'Almassi', 'Kenza', '51 rue de la Grèce', '82200', 'Moissac','almassik@bio.org','0563117984');
INSERT INTO agent VALUES ('0D181', 'Kidjo', 'Yves', '654 boulevard de la gare', '31000', 'Toulouse','mister_k@lagare.net','0562000039');
INSERT INTO agent VALUES ('0A112', 'Bathelier', 'Pauline', '7 Impasse Lubat', '31770', 'Colomiers','missis_b@lagare.net','0561244978');
INSERT INTO agent VALUES ('0G652', 'Jalabert', 'Maxence', '18 place de la Monnaie', '46100', 'Figeac',NULL,'0565214787');
INSERT INTO agent VALUES ('0E292', 'Blanquet', 'Paul', '26 route des primeurs', '82300', 'Caussade', 'pblanquet@natura.com', '0563243648');
INSERT INTO agent VALUES ('0E193', 'Kremer', 'Olga', '77 rue des doriphores', '81000', 'Albi','olga212@larzac.net','0563100000');
INSERT INTO agent VALUES ('AC548', 'Marcenac', 'Sylvie', 'Route du Lévezou', '31200', 'Toulouse','sissimars@pareloup.fr','0621345721');

INSERT INTO prescripteur VALUES ('A0005', 'Maison de la nature', NULL, '1254, Avenue des reines du Poitou', '31200', 'Toulouse','nature4you@toulousecity.fr','0561000007');
INSERT INTO prescripteur VALUES ('AC587', 'Territoires  du quercy', 'Délégation du tourisme', '50, Place des cailloux', '46000', 'Cahors','quercy@midipyr.fr','0565464646');
INSERT INTO prescripteur VALUES ('AC547', 'ONF', NULL, '17, place de la flaune', '12100', 'Millau','sudav@dourbie.fr','0565111456');

INSERT INTO recensement VALUES ('R1208200122',TO_DATE('07/12/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE('07/19/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),'A0005','0A112') ;
INSERT INTO recensement VALUES ('R1208200124',TO_DATE('08/12/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE('09/12/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),'AC547', 'AC548') ;
INSERT INTO recensement VALUES ('R1208200129',TO_DATE('09/01/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE('09/15/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),'AC547', '0D181') ;
INSERT INTO recensement VALUES ('R1208200151',TO_DATE('09/10/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),TO_DATE('09/30/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'),'AC587', 'AC548') ;

INSERT INTO releve VALUES ('0A112','R1208200122','glechoma hederacea','46_GRM_lot1156',5,'pousse',10);
INSERT INTO releve VALUES ('0E193','R1208200122','orchidea flagada','46_FGC_lot1251',20,'adulte',95) ;
INSERT INTO releve VALUES ('0E193','R1208200122','orchidea flagada','46_GRM_lot1156',10,'adulte',95) ;
INSERT INTO releve VALUES ('0G652','R1208200129','bisutella laevigata','12_RDZ_lot2189',3,'pousse',14) ;
INSERT INTO releve VALUES ('0G652','R1208200129','bisutella laevigata','12_CPD_lot1005',10,'adulte',14);
INSERT INTO releve VALUES ('0G652','R1208200129','lamium album','46_FGC_lot1251',50,'adulte',160);
INSERT INTO releve VALUES ('0G652','R1208200124','glechoma hederacea','46_FGC_lot1251',50,'adulte',30) ;
INSERT INTO releve VALUES ('0F291','R1208200151','glechoma hederacea','46_FGC_lot1251',50,'adulte',30) ;
INSERT INTO releve VALUES ('0F291','R1208200151','glechoma hederacea','12_RDZ_lot2190',5,'pousse',10) ;


-- Validation de ce jeu de données

COMMIT;
