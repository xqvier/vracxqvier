/*
 * EssaiCollections.java                                    25 oct. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.pattern.collections;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Découvrir des paterns dans les collections
 * @author x.mourgues
 *
 */
public class EssaiCollections {

    /**
     * Manipulation polymorphe de collections.
     * @param args Liste d'arguments du programme
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // Hiérarchie des list
        LinkedList<String> texte = new LinkedList<String>();
        texte.add("une première ligne");
        texte.add("une deuxième ligne");
        
        List<String> complete = new LinkedList<String>();
        complete.addAll(texte);
        complete.addAll((Collection<? extends String>) texte.clone());
        
        System.out.println("Texte : ");
        System.out.println(texte);
        
        System.out.println("\nComplete : ");
        System.out.println(complete);
    }

}
