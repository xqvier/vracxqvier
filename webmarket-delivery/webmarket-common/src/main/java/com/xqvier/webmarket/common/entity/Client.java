/*
 * Client.java                                    27 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.common.entity;

/**
 * Classe représentant un client en base.
 * @author Xavier Mourgues
 *
 */
public class Client {
    private String userName;
    
    private String password;
    
    private String firstName;
    
    private String lastName;
    
    
    /**
     * Default Constructor
     */
    public Client() {
    }


    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }


    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }


    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Client [userName=" + userName + ", password=" + password
                + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
    
    
    
}
