/*
 * DAOFactory.java                                    18 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.business.core;

import com.xqvier.webmarket.business.dao.ClientDAO;
import com.xqvier.webmarket.business.dao.ProductDAO;
import com.xqvier.webmarket.common.entity.Client;
import com.xqvier.webmarket.common.entity.Product;

/**
 * Classe de fabrication des DAO pour la partie présentation
 * @author Xavier Mourgues
 *
 */
public class DAOFactory {
    /**
     * Retourne un objet DAO gérant les produits
     * @return le dao du produit
     */
    public static AbstractDAO<Product> getProductDAO(){
        return new ProductDAO();
    }
    
    
    /**
     * Retourne un objet DAO gérant les clients
     * @return le dao du client
     */
    public static AbstractDAO<Client> getClientDAO(){
        return new ClientDAO();
    }
    
}
