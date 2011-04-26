/*
 * PoidsManager.java	15 avr. 2011
 * xMourgues 
 */
package com.xqvier.muscu.weight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xqvier.muscu.R;

/**
 * TODO Comment class
 * 
 * @author xMourgues
 * @version
 */
public class WeightManager extends Activity {

    /** Widget - EditText poids rentré par l'utilisateur */
    private EditText weightEditBox;
    
    /** Widget - Button d'enregistrement du poids */
    private Button saveButton;
    
    /** Widget - Button d'affichage des stats */
    private Button showStatButton;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.weight_manager);

	weightEditBox = (EditText) findViewById(R.id.weightEditBox);
	saveButton = (Button) findViewById(R.id.saveButton);
	showStatButton = (Button) findViewById(R.id.showStatButton);
	
	saveButton.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		save();
	    }
	});
	showStatButton.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		showStat();
	    }
	});
    }

    /**
     * TODO Comment method
     *
     */
    protected void showStat() {
	Intent intent = new Intent(this, WeightStats.class);
	startActivity(intent);
	
    }

    /**
     * TODO Comment method
     *
     */
    protected void save() {
	Toast mToast;
	if("".equals(weightEditBox.getText().toString())){
	    mToast = Toast.makeText(this, R.string.error_weight, Toast.LENGTH_LONG);
	    mToast.show();
	    return;
	}	
	// else
	new Weight(Float.parseFloat(weightEditBox.getText().toString()));
    }

}
