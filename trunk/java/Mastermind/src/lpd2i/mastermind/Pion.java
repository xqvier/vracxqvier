/*
 * Pion.java		24 janv. 2011
 * (c) Xavier Mourgues 
 */
package lpd2i.mastermind;

/**
 * TODO comment class
 *
 * @author Xavier Mourgues
 * @version 
 */
public class Pion {
    private String value;
    
    public Pion(String value){
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    public boolean equals(Pion p) {
        return this.value.equals(p);
    }
    
}
