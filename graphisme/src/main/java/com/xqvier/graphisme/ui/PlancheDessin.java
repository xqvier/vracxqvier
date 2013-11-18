/*
 * PlancheDessin.java                                    14 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.graphisme.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.xqvier.graphisme.pojo.Figure;

/**
 * TODO comment class responsabilities
 * 
 * @author Administrateur
 * 
 */
public class PlancheDessin extends JPanel implements Observer {
    /** TODO comment field role          */
    private static final long serialVersionUID = 1L;
    protected Figure aDessiner;

    /**
     * TODO comment initialization state
     * @param dessin Le dessin
     */
    public PlancheDessin(Figure dessin) {
        this.setSize(new Dimension(800, 700));
        this.setBackground(Color.RED);
        aDessiner = dessin;
        aDessiner.addObserver(this);
        
    }

    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        aDessiner.dessin(g2D);
    }
    
    
    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    public void update(Observable o, Object arg) {
        
        
    }

}
