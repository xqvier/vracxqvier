/*
 * Figure.java                                    14 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.graphisme.pojo;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Figure 2D assemblage de plusieurs forme
 * 
 * @author Administrateur
 * 
 */
public class Figure extends Forme implements Iterable<Forme> {

    protected List<Forme> formes;

    /**
     * Initialise une figure vide
     */
    public Figure() {
        super();
        formes = new LinkedList<Forme>();
    }

    /**
     * Ajout une forme à dessiner après les autres formes de cette figure
     * @param pForme La forme à ajouter.
     */
    public void ajouter(Forme pForme) {
        formes.add(pForme);
        notifyObservers();
    }
    
    public int nbForme(){
        int sum = 0;
        for(Forme forme : formes){
            sum+=forme.nbForme();
        }
        return sum;
    }
    
    /* (non-Javadoc)
     * @see com.xqvier.graphisme.pojo.Forme#dessin(java.awt.Graphics2D)
     */
    @Override
    public void dessin(Graphics2D contexte) {
        for(Forme aTracer : formes){
            aTracer.dessin(contexte);
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<Forme> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

}
