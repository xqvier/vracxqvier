/*
 * Product.java                                    18 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.business.entity;

/**
 * TODO comment class responsabilities
 * @author Administrateur
 *
 */
public class Product {
    private int id;
    
    private String name;
    
    private double price;

    /**
     * TODO comment initialization state
     * @param id L'identifiant du produit
     * @param name Le nom du produit
     * @param price Le prix du produit
     */
    public Product(int id, String name, double price) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price
                + "]";
    }
    
    
    
}
