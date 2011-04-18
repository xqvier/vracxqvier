/*
 * Weight.java	18 avr. 2011
 * xMourgues 
 */
package com.xqvier.muscu.weight;

import java.util.Calendar;
import java.util.Date;

/**
 * TODO Comment class
 *
 * @author xMourgues
 * @version 
 */
public class Weight {
    private float weight;
    
    private Date date;
    
    /**
     * TODO Comment constructor
     *
     */
    public Weight(float weight) {
	this.weight = weight;
	this.date = Calendar.getInstance().getTime();
	System.out.println(this.weight + " @ " + this.date);
    } 
    
}
