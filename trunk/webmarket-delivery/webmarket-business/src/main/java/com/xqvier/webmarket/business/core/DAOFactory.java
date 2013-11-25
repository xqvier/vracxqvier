/*
 * DAOFactory.java                                    18 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.business.core;

import com.xqvier.webmarket.business.dao.ProductDAO;
import com.xqvier.webmarket.business.entity.Product;

/**
 * TODO comment class responsabilities
 * @author Administrateur
 *
 */
public class DAOFactory {
    /**
     * TODO comment role
     * @return le dao du produit
     */
    public static AbstractDAO<Product> getProductDAO(){
        return new ProductDAO();
    }
}
