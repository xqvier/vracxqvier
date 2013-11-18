/*
 * Forme.java                                    14 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.graphisme.pojo;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

/**
 * TODO comment class responsabilities
 * 
 * @author Administrateur
 * 
 */
public abstract class Forme extends Observable implements Dessinable, Shape {

    protected Shape forme2D;

    /**
     * TODO comment initialization state
     * 
     * @param forme2d
     *            forme 2D
     */
    public Forme(Shape forme2d) {
        // TODO Auto-generated constructor stub
        this.forme2D = forme2d;
    }
    
    /**
     * TODO comment initialization state
     */
    public Forme() {
        super();
    }
    
    /**
     * Renvoie le nombre de forme
     * @return Le nombre de forme
     */
    public int nbForme(){
        return 1;
    }
    
    /* (non-Javadoc)
     * @see com.xqvier.graphisme.pojo.Dessinable#dessin(java.awt.Graphics2D)
     */
    public void dessin(Graphics2D contexte) {
        if(forme2D != null){
            contexte.draw(forme2D);
        }
        
    }

    /**
     * {@inheritDoc}
     */
    public Rectangle getBounds() {
        return forme2D.getBounds();
    }

    /**
     * {@inheritDoc}
     */
    public Rectangle2D getBounds2D() {
        return forme2D.getBounds2D();
    }

    /**
     * {@inheritDoc}
     */
    public boolean contains(double x, double y) {
        return forme2D.contains(x, y);
    }

    /**
     * {@inheritDoc}
     */
    public boolean contains(Point2D p) {
        return forme2D.contains(p);
    }

    /**
     * {@inheritDoc}
     */
    public boolean intersects(double x, double y, double w, double h) {
        return forme2D.intersects(x, y, w, h);
    }

    /**
     * {@inheritDoc}
     */
    public boolean intersects(Rectangle2D r) {
        return forme2D.intersects(r);
    }

    /**
     * {@inheritDoc}
     */
    public boolean contains(double x, double y, double w, double h) {
        return forme2D.contains(x, y, w, h);
    }

    /**
     * {@inheritDoc}
     */
    public boolean contains(Rectangle2D r) {
        return forme2D.contains(r);
    }

    /**
     * {@inheritDoc}
     */
    public PathIterator getPathIterator(AffineTransform at) {
        return forme2D.getPathIterator(at);
    }

    /**
     * {@inheritDoc}
     */
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return forme2D.getPathIterator(at, flatness);
    }
    
    
    
    
}
