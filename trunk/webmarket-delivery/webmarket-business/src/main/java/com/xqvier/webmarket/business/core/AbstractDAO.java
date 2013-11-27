/*
 * AbstractDAO.java                                    18 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.business.core;

import java.util.List;

import javax.sql.DataSource;

/**
 * Classe abstraite d'un DAO.
 * 
 * @author Xavier Mourgues
 * @param <T>
 *            La classe de l'entité
 * 
 */
public abstract class AbstractDAO<T> {
    /** Datasource du serveur d'application. */
    protected static final DataSource ds = WebmarketJDBC.getDataSource();

    
    /**
     * Créé un enregistrement dans la table
     * @param obj L'objet à crée
     * @return l'objet créé
     */
    public abstract T create(T obj); 
    /**
     * Met à jour un enregistrement dans la table
     * @param obj L'objet à mettre à jour
     * @return L'objet mis à jour
     */
    public abstract T update(T obj); 
    /**
     * Supprimer un enregistrement dans la table
     * @param obj L'objet à supprimer
     */
    public abstract void delete(T obj);
    /**
     * Cherche un enregistrement en fonction de son identifiant
     * @param id L'identifiant de l'enregistrement à trouver
     * @return L'objet représentant l'enregistrement en table
     */
    public abstract T find(long id); 
    /**
     * Recherche la liste des enregistrement de la table
     * @return la liste d'objet représentant les enregistrement.
     */
    public abstract List<T> findAll();
}
