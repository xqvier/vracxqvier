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
 * TODO comment class responsabilities
 * 
 * @author Administrateur
 * 
 */
public class WebmarketJDBC {

    private static DataSource ds = null;

    /**
     * TODO comment initialization state
     */
    private WebmarketJDBC() {
        // TODO Auto-generated constructor stub
    }

    /**
     * TODO comment role
     * 
     * @return La datasource
     */
    public static DataSource getDataSource() {
        initialize();
        return ds;

    }

    /**
     * TODO comment role
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
