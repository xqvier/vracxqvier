/*
 * Combinaise.java		24 janv. 2011
 * (c) Xavier Mourgues 
 */
package lpd2i.mastermind;

import java.util.ArrayList;

/**
 * TODO comment class
 *
 * @author Xavier Mourgues
 * @version 
 */
public class Combinaison {
    private ArrayList<Pion> list;
    
    public Combinaison(String a, String b, String c, String d) {
        this();
        list.add(new Pion(a));
        list.add(new Pion(b));
        list.add(new Pion(c));
        list.add(new Pion(d));
    }
    
    public Combinaison(Pion a, Pion b, Pion c, Pion d) {
        this();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
    }
    
    public Combinaison(){
        list = new ArrayList<Pion>();
    }
}
