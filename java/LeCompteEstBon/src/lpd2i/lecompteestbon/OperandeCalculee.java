/*
 * OperandeCalculee.java		24 janv. 2011
 * (c) Xavier Mourgues 
 */
package lpd2i.lecompteestbon;

/**
 * TODO comment class
 *
 * @author Xavier Mourgues
 * @version 
 */
public class OperandeCalculee extends Operande {

    private Operande gauche;
    private Operande droite;
    private Operateur op;
    /**
     * TODO commenter et indiquer l'état initial visé par ce constructeur
     * @param i
     */
    public OperandeCalculee(int i, Operande gauche, Operande droite, Operateur op) {
        super(i);
        this.gauche = gauche;
        this.droite = droite;
        this.op = op;
    }
}
