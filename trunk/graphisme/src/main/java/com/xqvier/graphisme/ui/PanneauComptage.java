/*
 * PanneauComptage.java                                    14 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.graphisme.ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.xqvier.graphisme.pojo.Figure;

/**
 * TODO comment class responsabilities
 * @author Administrateur
 *
 */
public class PanneauComptage extends JPanel implements Observer {
    /** TODO comment field role          */
    private static final long serialVersionUID = 1L;

    protected JTextField nombre;
    
    protected Figure aDessiner;
    
    /**
     * TODO comment initialization state
     * @param pDessin dessin
     */
    public PanneauComptage(Figure pDessin) {
        aDessiner = pDessin;
        aDessiner.addObserver(this);
        this.add(new JLabel("Nombre de forme : "));
        nombre = new JTextField(pDessin.nbForme());
        this.add(nombre);
        
        
    }

    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    public void update(Observable o, Object arg) {
        nombre.setText(Integer.toString(((Figure) o).nbForme()));
    }
}
