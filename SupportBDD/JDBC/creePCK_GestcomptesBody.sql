
CREATE OR REPLACE PACKAGE BODY  gestcomptes  IS

-- Voir les spécifications pour les déclarations publiques comme les constantes et les erreurs

-- EXCEPTIONS
 xcompte_inexistant	EXCEPTION ;
 xsolde_insuffisant	EXCEPTION ;
 xmontant_invalide	EXCEPTION ;
 xplafond_depasse	EXCEPTION ;
 xcomptes_nondistincts 	EXCEPTION ;
 xa_solder		EXCEPTION ;

-- Clause pour récupérer une erreur Oracle et la traiter dans l’application
 PRAGMA EXCEPTION_INIT( xplafond_depasse , -01433 ) ;


FUNCTION compte_existactif (lecompte IN CHAR) RETURN BOOLEAN IS
-- Teste l'existence et la validité (statut actif 'A') de "lecompte"
-- RETOURNE VRAI ou FAUX
   unstatut compte.statut%TYPE ;
BEGIN
   SELECT statut INTO unstatut
   FROM compte WHERE numcpt = lecompte ;
   RETURN (unstatut = 'A') ;
EXCEPTION
   WHEN NO_DATA_FOUND THEN RETURN FALSE ;
END compte_existactif ;


FUNCTION montant_valide (lavaleur IN NUMERIC) RETURN BOOLEAN IS
-- Contrôle que la valeur soit comprise entre seuil_montant et plafond_montant
-- RETOURNE VRAI ou FAUX
BEGIN
   RETURN ( (lavaleur >= cte_seuil_montant) AND (lavaleur <= cte_plafond_montant) ) ;
END montant_valide ;


FUNCTION numeroter (leclient IN VARCHAR) RETURN CHAR IS
-- Attribue un nouveau numéro de compte en fonction d'un numéro de client et du contenu BD
-- Attention, on puise dans compte et dans archivecompte
-- RETOURNE un numéro de compte construit à partir des initiales de la personne et de chiffres, 
-- de 1 a 99, on ne gère pas les cas > 99 (sans intérêt pour notre étude de cas)
unnom		client.nom%TYPE ;
unprenom	client.prenom%TYPE ;
initiales	CHAR(3) ; -- Les 3 premières lettres
numdispo	CHAR(2) ;
nummaxcpt	NUMERIC(2) ;
nummaxarccpt	NUMERIC(2) ;
nummax		NUMERIC(2) ;
BEGIN
	-- Un nom est au moins composé de 2 lettres. 
	-- Les attributs nom et prénom contiennent toujours une valeur.
	SELECT nom, prenom
		INTO unnom, unprenom 
		FROM client WHERE numcli=leclient ;
	initiales := SUBSTR(UPPER(unnom),1,2) || SUBSTR(UPPER(unprenom),1,1) ;
	SELECT NVL(MAX(SUBSTR(numcpt,4,2)+1),1) 
		INTO nummaxcpt
		FROM compte WHERE numcpt LIKE (initiales||'%') ;
	SELECT NVL(MAX(SUBSTR(numcpt,4,2)+1),1) 
		INTO nummaxarccpt
		FROM archivecompte WHERE numcpt LIKE (initiales||'%') ;
	IF nummaxcpt > nummaxarccpt THEN
		nummax := nummaxcpt;
	ELSE
		nummax := nummaxarccpt;
	END IF ;
	IF nummax < 10 THEN
		numdispo := '0' || nummax ;
	ELSE
		-- on ne gère pas le cas > 99
		numdispo := nummax ;
	END IF ;
	RETURN (initiales||numdispo) ;
END numeroter;


FUNCTION solde (lecompte IN CHAR) RETURN NUMERIC IS
-- RETOURNE le solde de "lecompte"
-- ERREUR ERR_CPT_INEXIST SI "lecompte" n'existe pas ou est invalide
unsolde compte.solde%TYPE ;
BEGIN
	IF (NOT compte_existactif (lecompte)) THEN
		RAISE xcompte_inexistant ;
	ELSE
		SELECT solde INTO unsolde
			FROM compte WHERE numcpt = lecompte ;
	RETURN unsolde ;
	END IF ;
EXCEPTION
	WHEN xcompte_inexistant THEN
		RAISE_APPLICATION_ERROR(ERR_CPT_INEXIST, 'COMPTE BANCAIRE ' || lecompte ||' INEXISTANT OU INVALIDE.') ;
END solde ;


PROCEDURE ouvrir (leclient IN CHAR, lecompte OUT CHAR) IS
-- Ouvre un compte en attribuant un numero
-- ERREUR ERR_CLI_INEXIST si leclient n'existe pas
-- Les deux lignes suivantes = Additif JDBC seulement
-- En commentaire et utilisation lecompte, leclient sinon
	leclient6	CHAR(6);
	lecompte5	CHAR(5);
BEGIN
	lecompte:= numeroter(leclient) ;
	lecompte5:= SUBSTR(lecompte,1,5 ) ;
	leclient6 := SUBSTR(leclient,1,6 ) ;
	INSERT INTO compte VALUES (lecompte5, 'A' , 0 , SYSDATE, leclient6) ;
	COMMIT ;
EXCEPTION
	WHEN NO_DATA_FOUND THEN
		RAISE_APPLICATION_ERROR(ERR_CLI_INEXIST, MSG_CLI_INEXIST) ;
END ouvrir;


PROCEDURE cloturer (lecompte IN CHAR) IS
-- Ferme "lecompte" en stockant ses informations dans la relation d'archive "archivecompte"
-- ERREUR ERR_CPT_INEXIST SI lecompte n'existe pas ou est invalide
-- ERREUR ERR_CPT_PAS_NULL SI lesolde n'est pas nul 
	lesolde compte.solde%TYPE ;
BEGIN
	IF (NOT compte_existactif (lecompte)) THEN
		RAISE xcompte_inexistant ;
	ELSE
		lesolde := solde (lecompte) ;
	IF (lesolde <> 0) THEN
		RAISE xa_solder ;
	END IF ;
	INSERT INTO archivecompte
		SELECT cpt.numcpt, cpt.date_ouv, SYSDATE, cli.numcli, cli.nom, cli.prenom 
		FROM compte cpt, client cli 
		WHERE cpt.numcpt = lecompte
		AND cpt.numcli = cli.numcli ;
	UPDATE compte 
		SET statut='C'
		WHERE numcpt = lecompte ;
	COMMIT ;
	END IF ;
EXCEPTION
	WHEN xa_solder THEN
		RAISE_APPLICATION_ERROR ( ERR_CPT_PAS_NULL, MSG_CPT_PAS_NULL );
	WHEN xcompte_inexistant THEN
		RAISE_APPLICATION_ERROR ( ERR_CPT_INEXIST,'COMPTE BANCAIRE ' || lecompte ||' INEXISTANT OU INVALIDE.' ) ;
END cloturer;


PROCEDURE deposer (lecompte IN CHAR, lemontant IN NUMERIC) IS
-- Ajoute "montant" au solde du "lecompte"
-- ERREUR ERR_CPT_SOLDE_MAX SI le compte dépasse sa capacite
-- ERREUR ERR_CPT_MONTANT_PB SI "lemontant" est incorrect
-- ERREUR ERR_CPT_INEXIST SI "lecompte" n'existe pas ou est invalide
	numtrans INTEGER ;
BEGIN
	IF (NOT compte_existactif (lecompte)) THEN
		RAISE xcompte_inexistant ;
	ELSIF (montant_valide(lemontant)) THEN
		INSERT INTO histotrans 
			VALUES (seq_transac.NEXTVAL, lecompte, NULL, lemontant, SYSDATE, USER) ;
		UPDATE compte SET solde = solde + lemontant WHERE numcpt = lecompte ;
		COMMIT ;
	ELSE
		RAISE xmontant_invalide ;
	END IF ;
EXCEPTION
	WHEN xcompte_inexistant THEN
		RAISE_APPLICATION_ERROR(ERR_CPT_INEXIST, 'COMPTE BANCAIRE ' || lecompte ||' INEXISTANT OU INVALIDE.') ;
	WHEN xplafond_depasse THEN
		RAISE_APPLICATION_ERROR(ERR_CPT_SOLDE_MAX, MSG_CPT_SOLDE_MAX) ;
	WHEN xmontant_invalide THEN
		RAISE_APPLICATION_ERROR(ERR_CPT_MONTANT_PB, 
'LE MONTANT DE VERSEMENT DOIT ETRE COMPRIS ENTRE '||cte_seuil_montant||' ET '||cte_plafond_montant||' EUROS. OPERATION IMPOSSIBLE') ;
END deposer;


PROCEDURE retirer (lecompte IN CHAR, lemontant IN NUMERIC) IS
-- Soustrait "lemontant" au solde du "compte"
-- Le seuil de decouvert est une constante
-- ERREUR ERR_CPT_SOLDE_MIN SI le solde de "lecompte" n'est pas suffisant pour l'operation
-- ERREUR ERR_CPT_MONTANT_PB SI "lemontant" est trop grand
-- ERREUR ERR_CPT_INEXIST SI "lecompte" n'existe pas ou est invalide
	numtrans INTEGER ;
	lesolde compte.solde%TYPE ;
BEGIN
	IF (NOT compte_existactif (lecompte)) THEN
		RAISE xcompte_inexistant ;
	ELSIF (montant_valide(lemontant)) THEN
		lesolde := solde(lecompte) ;
		IF lesolde - lemontant > (- cte_decouvert_autorise) THEN
			INSERT INTO histotrans 
				VALUES (seq_transac.NEXTVAL, lecompte, NULL, - lemontant, SYSDATE, USER) ;
			UPDATE compte SET solde = solde - lemontant WHERE numcpt = lecompte ;
			COMMIT ;
		ELSE
			RAISE xsolde_insuffisant ;
		END IF ;
	ELSE
		RAISE xmontant_invalide ;
	END IF ;
EXCEPTION
	WHEN xcompte_inexistant THEN
		RAISE_APPLICATION_ERROR(ERR_CPT_INEXIST, 'COMPTE BANCAIRE ' || lecompte ||' INEXISTANT OU INVALIDE.') ;
	WHEN xsolde_insuffisant THEN
		RAISE_APPLICATION_ERROR(ERR_CPT_SOLDE_MIN, MSG_CPT_SOLDE_MIN) ;
	WHEN xmontant_invalide THEN
		RAISE_APPLICATION_ERROR(ERR_CPT_MONTANT_PB, 
			'LE MONTANT DE VERSEMENT DOIT ETRE COMPRIS ENTRE '||cte_seuil_montant||' ET '||cte_plafond_montant||' EUROS. OPERATION IMPOSSIBLE') ;
END retirer;


PROCEDURE transferer (ledebiteur IN CHAR, lecrediteur IN CHAR, lemontant IN NUMERIC) IS
-- Ajoute "lemontant" au solde du "créditeur"
-- Et soustrait "lemontant" au solde du "débiteur"
-- Le seuil de decouvert apour valeur cte_decouvert_autorise(constante de package : 100 euros)
-- ERREUR ERR_CPT_SOLDE_MIN SI le solde de "lecompte" n'est pas suffisant pour l'opération
-- ERREUR ERR_CPT_SOLDE_MAX SI le compte dépasse sa capacité de stockage
-- ERREUR ERR_CPT_MONTANT_PB SI "lemontant" est trop grand
-- ERREUR ERR_CPT_INEXIST SI un des comptes n'existe pas ou est invalide
-- ERREUR ERR_CPT_NONDISTINC SI le virement est demandé entre deux comptes non distincts
	cptencours compte.numcpt%TYPE ;
	lesolde compte.solde%TYPE ;
BEGIN
	IF (ledebiteur=lecrediteur) THEN
		RAISE xcomptes_nondistincts ;
	ELSE
		cptencours := ledebiteur ;
		IF (NOT compte_existactif (ledebiteur)) THEN
			RAISE xcompte_inexistant ;
		ELSE
			cptencours := lecrediteur ;
			IF (NOT compte_existactif (lecrediteur)) THEN
				RAISE xcompte_inexistant ;
			END IF ;
		END IF ;
		IF (montant_valide(lemontant)) THEN
			lesolde := solde(ledebiteur) ;
			IF lesolde - lemontant > (- cte_decouvert_autorise)THEN
				INSERT INTO histotrans 
				VALUES (seq_transac.NEXTVAL, ledebiteur, lecrediteur, -lemontant, SYSDATE, USER) ;
				UPDATE compte SET solde = solde - lemontant WHERE numcpt = ledebiteur ;
				UPDATE compte SET solde = solde + lemontant WHERE numcpt = lecrediteur ;
				COMMIT ;
			ELSE
				RAISE xsolde_insuffisant ;
			END IF ;
		ELSE
			RAISE xmontant_invalide ;
		END IF ;
	END IF ;
EXCEPTION
	WHEN xmontant_invalide THEN
		RAISE_APPLICATION_ERROR(ERR_CPT_MONTANT_PB, 'LE MONTANT DE VERSEMENT DOIT ETRE COMPRIS ENTRE '||cte_seuil_montant||' ET '||cte_plafond_montant||' EUROS. OPERATION IMPOSSIBLE') ;
	WHEN xplafond_depasse THEN
		RAISE_APPLICATION_ERROR(ERR_CPT_SOLDE_MAX, MSG_CPT_SOLDE_MAX) ;
	WHEN xsolde_insuffisant THEN
		RAISE_APPLICATION_ERROR(ERR_CPT_SOLDE_MIN, MSG_CPT_SOLDE_MIN) ;
	WHEN xcompte_inexistant THEN
		RAISE_APPLICATION_ERROR(ERR_CPT_INEXIST, 'COMPTE BANCAIRE ' || cptencours ||' INEXISTANT OU INVALIDE.') ;
	WHEN xcomptes_nondistincts THEN
		RAISE_APPLICATION_ERROR(ERR_CPT_NONDISTINC, MSG_CPT_NONDISTINC) ;
END transferer ;



END gestcomptes ;
/


