/*
 * Rectangle.java                                    14 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.graphisme.pojo;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 * TODO comment class responsabilities
 * @author Administrateur
 *
 */
public class Rectangle extends Forme {
    /**
     * TODO comment initialization state
     * @param csg Le point supperieur gauche
     * @param largeur la largeur
     * @param hauteur la hauteur
     */
    public Rectangle(Point csg, int largeur, int hauteur){
        forme2D = new Rectangle2D.Double(csg.x, csg.y, largeur, hauteur);
    }
}
