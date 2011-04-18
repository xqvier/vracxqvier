/*
 * PoidsManager.java	15 avr. 2011
 * xMourgues 
 */
package com.xqvier.muscu.weight;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * TODO Comment class
 *
 * @author xMourgues
 * @version 
 */
public class WeightManager extends Activity {

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	TextView tv = new TextView(this);
	tv.setText("Poids");
	setContentView(tv);
    }
    
    

}
