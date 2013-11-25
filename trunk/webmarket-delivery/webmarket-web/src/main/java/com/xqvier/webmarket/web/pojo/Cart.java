/*
 * Panier.java                                    25 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.web.pojo;

import java.util.List;

import com.xqvier.webmarket.business.entity.Product;

/**
 * TODO comment class responsabilities
 * 
 * @author Administrateur
 * 
 */
public class Cart {
    private List<Product> products;

    /**
     * TODO comment initialization state
     * 
     * @param pProducts
     *            Liste des produits du panier
     */
    public Cart(List<Product> pProducts) {
        products = pProducts;
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

    /**
     * TODO comment role
     * 
     * @return Le cout total du panier
     */
    public double getTotal() {
        double result = 0;

        for (Product product : products) {
            result += product.getPrice();
        }

        result *= 100.0;
        result = Math.round(result);
        result /= 100.0;
        return result;
    }

    /**
     * TODO comment role
     * 
     * @return Le nombre d'article dans le panier
     */
    public int getNombreArticles() {
        return products.size();
    }

}
