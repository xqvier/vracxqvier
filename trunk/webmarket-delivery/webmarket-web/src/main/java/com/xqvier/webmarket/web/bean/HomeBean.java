/*
 * HomeBean.java                                    18 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.web.bean;

import java.util.List;

import com.xqvier.webmarket.business.core.DAOFactory;
import com.xqvier.webmarket.business.entity.Product;

/**
 * TODO comment class responsabilities
 * 
 * @author Administrateur
 * 
 */
public class HomeBean {
    List<Product> products;
    
    

    /**
     * TODO comment initialization state
     */
    public HomeBean() {
        products = DAOFactory.getProductDAO().findAll();
    }

    /**
     * @return the products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param products
     *            the products to set
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
