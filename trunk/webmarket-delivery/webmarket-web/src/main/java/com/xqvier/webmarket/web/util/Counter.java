/*
 * Counter.java                                    18 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.web.util;

/**
 * TODO comment class responsabilities
 * @author Administrateur
 *
 */
public class Counter {
    
    private int count;
    
    /**
     * TODO comment initialization state
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
