/*
 * PanneauDescription.java                                    14 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.graphisme.ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JList;
import javax.swing.JPanel;

import com.xqvier.graphisme.pojo.Figure;

/**
 * TODO comment class responsabilities
 * @author Administrateur
 *
 */
public class PanneauDescription extends JPanel implements Observer {
    /** TODO comment field role          */
    private static final long serialVersionUID = 1L;

    protected Figure aDessiner;
    
    protected JList<String> list = new JList<String>();
    /**
     * TODO comment initialization state
     * @param pDessin le dessin
     */
    public PanneauDescription(Figure pDessin) {
        aDessiner = pDessin;
        aDessiner.addObserver(this);
        this.add(list);
        this.add(new PanneauComptage(pDessin));
        
    }

    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        
    }
}
