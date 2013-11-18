/*
 * Graphisme.java                                    14 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.graphisme;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.xqvier.graphisme.pojo.Ellipse;
import com.xqvier.graphisme.pojo.Figure;
import com.xqvier.graphisme.pojo.Forme;
import com.xqvier.graphisme.pojo.Rectangle;
import com.xqvier.graphisme.ui.PanneauDescription;
import com.xqvier.graphisme.ui.PlancheDessin;

/**
 * TODO comment class responsabilities
 * 
 * @author Administrateur
 * 
 */
public class Graphisme {
    /**
     * TODO comment role
     * 
     * @param args
     *            argument du programme
     */
    public static void main(String[] args) {
        // Création des POJO

        Figure dessin = initialiserFigure();

        // UI
        JFrame fenetre = new JFrame();
        fenetre.setSize(500, 400);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setTitle("Titre");

        fenetre.add(new JScrollPane(new PlancheDessin(dessin)));

        /*JSplitPane panneauDessin = new JSplitPane();
        contentPane.add(panneauDessin);

        panneauDessin.add(new JScrollPane(new PlancheDessin(dessin)));
        PanneauDescription info = new PanneauDescription(dessin);
        panneauDessin.add(info);        
        */

        fenetre.setVisible(true);
    }

    /**
     * TODO comment role
     * 
     * @return
     */
    private static Figure initialiserFigure() {
        Figure bonhomme = new Figure();
        
        Figure tete = new Figure();
        tete.ajouter(new Ellipse(new Point(150,50), 300, 100));
        //yeux
        tete.ajouter(new Ellipse(new Point(220, 70),30,30));
        tete.ajouter(new Ellipse(new Point(350, 70),30,30));
        //bouche
        tete.ajouter(new Rectangle(new Point(250, 115), 100, 5));
        
        
        
        bonhomme.ajouter(tete);
        bonhomme.ajouter(new Ellipse(new Point(100, 150), 400, 500));
        
        
        
        return bonhomme;
    }
}
