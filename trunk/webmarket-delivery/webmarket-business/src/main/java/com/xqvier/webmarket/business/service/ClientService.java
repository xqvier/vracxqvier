package com.xqvier.webmarket.business.service;

import java.util.List;

import javax.ejb.Stateless;

import com.xqvier.webmarket.business.core.DAOFactory;
import com.xqvier.webmarket.common.entity.Client;
import com.xqvier.webmarket.common.service.ClientServiceLocal;

/**
 * Session Bean implementation class UserService
 */
@Stateless
public class ClientService implements ClientServiceLocal{

    /**
     * Default constructor. 
     */
    public ClientService() {
    }

    /* (non-Javadoc)
     * @see com.xqvier.webmarket.business.service.UserServiceLocal#checkUserPassword(java.lang.String, java.lang.String)
     */
    /**
     * TODO comment role
     * @param pUserName Le nom d'utilisateur
     * @param pPassword Le mot de passe 
     * @return zzz
     */
    public boolean checkUserPassword(String pUserName, String pPassword) {
        List<Client> clients =  DAOFactory.getClientDAO().findAll();
        
        for(Client client : clients){
            if(client.getUserName().equals(pUserName) && client.getPassword().equals(pPassword)){
                return true;
            }
        }
        return false;
    }

    /* (non-Javadoc)
     * @see com.xqvier.webmarket.business.service.ClientServiceLocal#readClient(java.lang.String, java.lang.String)
     */
    /**
     * TODO comment role
     * @param pUserName zzz
     * @param pPassword zzz
     * @return zzz
     */
    public Client readClient(String pUserName, String pPassword) {
        if(checkUserPassword(pUserName, pPassword)){
            List<Client> clients =  DAOFactory.getClientDAO().findAll();
            
            for(Client client : clients){
                if(client.getUserName().equals(pUserName) && client.getPassword().equals(pPassword)){
                    return client;
                }
            }
        } 
        return null;
    }

}
