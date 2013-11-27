/*
 * Counter.java                                    18 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.web.util;

/**
 * Classe permettant de compter.
 * @deprecated non utilisé
 * @author Xavier Mourgues
 *
 */
public class Counter {
    
    private int count;
    
    /**
     * Constructeur par défaut, initialise le compteur à 0.
     */
    public Counter() {        
        super();
        count = 0;
    }

    /**
     * @return the count
     */
    public synchronized int getCount() {
        return count;
    }

    /**
     * @param pCount the count to set
     */
    public synchronized void setCount(int pCount) {
        this.count = pCount;
    }
    
    /**
     * Incrémente le compteur.
     * @return le nouveau count
     */
    public synchronized int increment() {
        count++;
        return count;
    }
    
    

}
