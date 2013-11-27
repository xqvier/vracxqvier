/*
 * ClientServiceLocal.java                                    27 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.common.service;

import javax.ejb.Local;

import com.xqvier.webmarket.common.entity.Client;

/**
 * TODO comment class responsabilities
 * @author Administrateur
 *
 */
@Local
public interface ClientServiceLocal {
    /**
     * TODO comment role
     * @param pUserName zz 
     * @param pPassword zz
     * @return zz
     */
    public boolean checkUserPassword(String pUserName, String pPassword);
    
    /**
     * TODO comment role
     * @param pUserName zz
     * @param pPassword zz
     * @return zz 
     */
    public Client readClient(String pUserName, String pPassword);
}
