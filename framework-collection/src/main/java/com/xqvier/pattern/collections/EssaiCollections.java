/*
 * EssaiCollections.java                                    25 oct. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.pattern.collections;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Découvrir des paterns dans les collections
 * 
 * @author x.mourgues
 * 
 */
public class EssaiCollections {

    /**
     * Manipulation polymorphe de collections.
     * 
     * @param args
     *            Liste d'arguments du programme
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
        System.out.println(texte.toString());

        System.out.println("\nComplete : ");
        System.out.println(complete.toString());

        try {
            ObjectOutputStream flot = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream("texte.bin")));
            flot.writeObject(texte);
            flot.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
