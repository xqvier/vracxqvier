/*
 * Plaquette.java		24 janv. 2011
 * (c) Xavier Mourgues 
 */
package lpd2i.lecompteestbon;

import java.util.ArrayList;

/**
 * TODO comment class
 *
 * @author Xavier Mourgues
 * @version 
 */
public class Plaquette {
    private ArrayList<Operande> list;
    
    public Plaquette() {
        for(int i = 1; i<=10;i++){
            list.add(new Operande(i));
            list.add(new Operande(i));
        }
        list.add(new Operande(25));
        list.add(new Operande(50));
        list.add(new Operande(75));
        list.add(new Operande(100));
    }
}
