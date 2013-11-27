/*
 * ProductServiceLocal.java                                    27 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.common.service;

import java.util.List;

import javax.ejb.Local;

import com.xqvier.webmarket.common.entity.Product;

/**
 * TODO comment class responsabilities
 * @author Administrateur
 *
 */
@Local
public interface ProductServiceLocal {
    /**
     * TODO comment role
     * @return zz
     */
    public List<Product> findAll();

    /**
     * TODO comment role
     * @param pId zz 
     * @return zz
     */
    public Product find(long pId);
    
    
    
}
