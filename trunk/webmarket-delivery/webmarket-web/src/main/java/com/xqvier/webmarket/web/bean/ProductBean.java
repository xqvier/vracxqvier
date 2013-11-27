/*
 * HomeBean.java                                    18 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.web.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import com.xqvier.webmarket.common.entity.Product;
import com.xqvier.webmarket.common.service.ProductServiceLocal;

/**
 * Bean associé à l'accueil du site
 * 
 * @author Xavier Mourgues
 * 
 */
public class ProductBean implements Serializable {
    /** Identifiant de sérialisation */
    private static final long serialVersionUID = 1L;

    private List<Product> products;

    
    /**
     * Constructeur par défaut, requete la liste des produits.
     */
    public ProductBean() {
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
