
DROP  PACKAGE	gestcomptes ;

CREATE PACKAGE	gestcomptes   IS
  
-- CONSTANTES

	cte_decouvert_autorise	CONSTANT NUMERIC(5,2)	:= 100.00 ;
	cte_seuil_montant       CONSTANT NUMERIC(3,2)	:= 0.01 ;
	cte_plafond_montant	CONSTANT NUMERIC(8,2)	:= 999999.99 ;

	ERR_CPT_MAXNB		CONSTANT NUMERIC(5) 	:= - 20001 ;
	ERR_CPT_SOLDE_MIN	CONSTANT NUMERIC(5)	:= - 20002 ;
	ERR_CPT_SOLDE_MAX	CONSTANT NUMERIC(5)	:= - 20004 ;
	ERR_CPT_MONTANT_PB	CONSTANT NUMERIC(5)	:= - 20005 ;
	ERR_CPT_INEXIST		CONSTANT NUMERIC(5)	:= - 20015 ;
	ERR_CPT_NONDISTINC	CONSTANT NUMERIC(5)	:= - 20016 ;
	ERR_CPT_PAS_NULL	CONSTANT NUMERIC(5)	:= - 20017 ;
	ERR_CLI_INEXIST		CONSTANT NUMERIC(5)	:= - 20018 ;
	ERR_HISTO_INEXIST	CONSTANT NUMERIC(5)	:= - 20019 ;
	ERR_NBOPER_INVALID	CONSTANT NUMERIC(5)	:= - 20020 ; 

	MSG_CPT_MAXNB		CONSTANT VARCHAR(100)	:=  'NUMERO DE COMPTE LIMITE A 2 CHIFFRES POUR NOTRE APPLICATION, 99 EST DEJA ATTEINT';
	MSG_CPT_SOLDE_MIN	CONSTANT VARCHAR(100)	:=  'PAS ASSEZ D''ARGENT POUR EFFECTUER L''OPERATION';
	MSG_CPT_SOLDE_MAX	CONSTANT VARCHAR(100)	:=  'COMPTE DEPASSANT SA CAPACITE, OPERATION IMPOSSIBLE' ;
	--MSG_CPT_MONTANT_PB	CONSTANT VARCHAR(100)	:=  Exploite dans le message le montant courant, constante impossible, voir code
	--MSG_CPT_INEXIST	CONSTANT VARCHAR(100)	:=  Exploite dans le message le compte concerné, constante impossible, voir code
	-- Un numéro de compte mentionné pour un retrait, un virement... ne correspond pas à un compte valide (statut='A')
	MSG_CPT_NONDISTINC	CONSTANT VARCHAR(100)	:= 'UN VIREMENT DOIT ETRE EFFECTUE ENTRE DEUX COMPTES DISTINCTS.' ;
	MSG_CPT_PAS_NULL	CONSTANT VARCHAR(100)	:= 'FERMETURE DU COMPTE IMPOSSIBLE. LE SOLDE DU COMPTE DOIT ETRE NUL, VEUILLEZ SOLDER LE COMPTE.'  ;
	MSG_CLI_INEXIST		CONSTANT VARCHAR(100)	:= 'CREATION DU COMPTE IMPOSSIBLE. LE NUMERO DE CLIENT EST-IL VALIDE ?'  ;
	-- Lors de la numérotation du compte, si le numéro de client mentionné n'existe pas.


	PROCEDURE ouvrir	(leclient IN CHAR, lecompte OUT CHAR) ;

	-- Ouvre un compte pour "leclient" en attribuant le numero "lecompte"
	-- ERREUR ERR_CLI_INEXIST	SI  "leclient" n'existe pas
   

	PROCEDURE cloturer	(lecompte IN CHAR) ;

	-- Ferme "lecompte" en stockant ses informations dans la relation d'archive "arccompte"
	-- ERREUR ERR_CPT_INEXIST	SI  "lecompte" n'existe pas ou est invalide
	-- ERREUR ERR_CPT_PAS_NULL	SI  le solde n'est pas nul 

	  
	PROCEDURE deposer	(lecompte IN CHAR, lemontant   IN NUMERIC) ;

	-- Ajoute "lemontant" au solde de "lecompte"
	-- ERREUR ERR_CPT_SOLDE_MAX	SI  "lecompte" depasse sa capacite
	-- ERREUR ERR_CPT_MONTANT_PB	SI  "lemontant" est incorrect
	-- ERREUR ERR_CPT_INEXIST	SI  "lecompte" n'existe pas ou est invalide

	  
	PROCEDURE retirer	(lecompte IN CHAR, lemontant   IN NUMERIC) ;

	-- Soustrait "lemontant" au solde de "lecompte"
	-- Le seuil de decouvert est une constante
	-- ERREUR ERR_CPT_SOLDE_MIN	SI  le solde de "lecompte" n'est pas suffisant pour l'operation
	-- ERREUR ERR_CPT_MONTANT_PB	SI  "lemontant" est trop grand
	-- ERREUR ERR_CPT_INEXIST	SI  "lecompte" n'existe pas ou est invalide

	   
	PROCEDURE transferer	(ledebiteur IN CHAR, lecrediteur IN CHAR, lemontant  IN NUMERIC) ;

	-- Ajoute "lemontant" au solde de "lecrediteur"
	-- Et soustrait "lemontant" au solde de "ledebiteur"
	-- Le seuil de decouvert a pour valeur decouvert_autorise  (constante de package : 100 euros au 20/10/2009)
	-- ERREUR ERR_CPT_SOLDE_MIN	SI  le solde de "ledebiteur" n'est pas suffisant pour l'operation
	-- ERREUR ERR_CPT_SOLDE_MAX	SI  "lecrediteur" depasse sa capacite de stockage
	-- ERREUR ERR_CPT_MONTANT_PB	SI  "lemontant" est trop grand
	-- ERREUR ERR_CPT_INEXIST	SI  un des comptes "ledebiteur" ou "lecrediteur" n'existe pas ou est invalide
	-- ERREUR ERR_CPT_NONDISTINC	SI  le virement est demande entre deux comptes non distincts : quand "ledebiteur"="lecrediteur"

  
	FUNCTION  solde	(lecompte IN CHAR) RETURN NUMERIC ;

	-- RETOURNE le solde de "lecompte"
	-- ERREUR ERR_CPT_INEXIST	SI  "lecompte" n'existe pas ou est invalide


END gestcomptes ;
/
