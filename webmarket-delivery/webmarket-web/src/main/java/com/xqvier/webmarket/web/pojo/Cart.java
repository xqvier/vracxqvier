/*
 * Panier.java                                    25 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.web.pojo;

import java.util.List;

import com.xqvier.webmarket.common.entity.Product;

/**
 * Classe représentant le panier d'un utilisateur
 * 
 * @author Xavier Mourgues
 * 
 */
public class Cart {
    private List<Product> products;

    /**
     * Constructeur initialisation un panier avec une liste de produit
     * 
     * @param pProducts
     *            La liste des produits du panier
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
     * Retourne le prix total du panier
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
     * Retourne le nombre d'article du panier
     * 
     * @return Le nombre d'article dans le panier
     */
    public int getNombreArticles() {
        return products.size();
    }

}
