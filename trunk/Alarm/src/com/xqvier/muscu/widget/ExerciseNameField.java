/*
 * ExerciseNameField.java	27 avr. 2011
 * xMourgues 
 */
package com.xqvier.muscu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.xqvier.muscu.DBAdapter;

/**
 * Sous classe de AutoCompleteTextView pour mettre à jour automatiquement les
 * noms des exercices
 * 
 * @author xMourgues
 * @version
 */
public class ExerciseNameField extends AutoCompleteTextView {
    private Context context;
    private String[] exoNames;

    /**
     * Constructeur par défaut
     * 
     * @param context
     */
    public ExerciseNameField(Context context) {
	super(context);
	
	this.context = context;
	init();

    }

    /**
     * Constructeur par défaut
     * 
     * @param context
     * @param attrs
     * @param defStyle
     */
    public ExerciseNameField(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);

	this.context = context;
	init();

    }

    /**
     * Constructeur par défaut
     * 
     * @param context
     * @param attrs
     */
    public ExerciseNameField(Context context, AttributeSet attrs) {
	super(context, attrs);

	this.context = context;
	init();

    }
    
    private void init() {
	DBAdapter db = new DBAdapter(context);
	db.open();
	exoNames = db.selectAllExoNames();
	db.close();

	ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
	        android.R.layout.simple_dropdown_item_1line, exoNames);
	this.setAdapter(adapter);
	this.setOnFocusChangeListener(new View.OnFocusChangeListener() {

	    @Override
	    public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
		    init();
		}

	    }
	});
    }

    /**
     * @return the exoNames
     */
    public String[] getExoNames() {
	return exoNames;
    }

}
