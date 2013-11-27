/*
 * UserBean.java                                    27 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.web.bean;

import com.xqvier.webmarket.common.entity.Client;

/**
 * Classe stockant les information de l'utilisateur logué
 * @author Xavier Mourgues
 *
 */
public class UserBean {
    
    private Client client = null;
    
    private boolean connected = false;

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the isConnected
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * @param isConnected the isConnected to set
     */
    public void setConnected(boolean isConnected) {
        this.connected = isConnected;
    }
    
    

}
