/*
 * WebmarketJDBC.java                                    18 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.business.core;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Classe singleton gérant le datasource du serveur d'application.
 * 
 * @author Xavier Mourgues
 * 
 */
public class WebmarketJDBC {

    private static DataSource ds = null;

    /**
     * Constructeur par défaut privé pour empêcher l'instanciation.
     */
    private WebmarketJDBC() {
    }

    /** 
     * @return La datasource
     */
    public static DataSource getDataSource() {
        initialize();
        return ds;

    }

    /**
     * Attache la datasource du serveur d'application à la classe.
     */
    private static void initialize() {
        if (ds == null) {
            try {
                Context initContexte = new InitialContext();
                ds = (DataSource) initContexte
                        .lookup("java:comp/env/jdbc/WebmarketDS");
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
