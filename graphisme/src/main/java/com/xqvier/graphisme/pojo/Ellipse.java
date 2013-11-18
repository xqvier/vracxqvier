/*
 * Ellipse.java                                    14 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.graphisme.pojo;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

/**
 * Ellipse 2D dessinable dans un contexte
 * @author Administrateur
 *
 */
public class Ellipse extends Forme {
    /**
     * Objet model d'une elipse
     * @param csg Le point superieur gauche
     * @param largeur largeur de l'ellipse
     * @param hauteur hauteur de l'ellipse 
     */
    public Ellipse(Point csg, int largeur, int hauteur) {
        super(new Ellipse2D.Double(csg.x, csg.y, largeur, hauteur));
    }
}
