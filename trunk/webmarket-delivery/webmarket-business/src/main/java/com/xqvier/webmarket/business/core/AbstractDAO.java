/*
 * AbstractDAO.java                                    18 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.business.core;

import java.util.List;

import javax.sql.DataSource;

/**
 * TODO comment class responsabilities
 * 
 * @author Administrateur
 * @param <T>
 *            La classe de l'entité
 * 
 */
public abstract class AbstractDAO<T> {
    /** TODO comment field role */
    protected static final DataSource ds = WebmarketJDBC.getDataSource();

    
    /**
     * TODO comment role
     * @param obj L'objet à crée
     * @return l'objet créé
     */
    public abstract T create(T obj); 
    /**
     * TODO comment role
     * @param obj t
     * @return t
     */
    public abstract T update(T obj); 
    /**
     * TODO comment role
     * @param obj t
     */
    public abstract void delete(T obj);
    /**
     * TODO comment role
     * @param id t
     * @return t
     */
    public abstract T find(long id); 
    /**
     * TODO comment role
     * @return t
     */
    public abstract List<T> findAll();
}
