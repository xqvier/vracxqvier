/*
 * ClientDAO.java                                    27 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.xqvier.webmarket.business.core.AbstractDAO;
import com.xqvier.webmarket.common.entity.Client;

/**
 * Classe gérant les requete sur les clients
 * 
 * @author Xavier Mourgues
 * 
 */
public class ClientDAO extends AbstractDAO<Client> {

    /**
     * Default constructor
     */
    public ClientDAO() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.xqvier.webmarket.business.core.AbstractDAO#create(java.lang.Object)
     */
    @Override
    public Client create(Client obj) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.xqvier.webmarket.business.core.AbstractDAO#update(java.lang.Object)
     */
    @Override
    public Client update(Client obj) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.xqvier.webmarket.business.core.AbstractDAO#delete(java.lang.Object)
     */
    @Override
    public void delete(Client obj) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.xqvier.webmarket.business.core.AbstractDAO#find(long)
     */
    @Override
    public Client find(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.xqvier.webmarket.business.core.AbstractDAO#findAll()
     */
    @Override
    public List<Client> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = ds.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM client";
            rs = statement.executeQuery(query);
            List<Client> clients = new LinkedList<Client>();
            Client client = null;
            while (rs.next()) {
                client = new Client();
                client.setUserName(rs.getString("username"));
                client.setPassword(rs.getString("password"));
                client.setFirstName(rs.getString("firstname"));
                client.setLastName(rs.getString("lastname"));
                clients.add(client);
            }
            return clients;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
