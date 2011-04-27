/*
 * ExerciseStats.java	27 avr. 2011
 * xMourgues 
 */
package com.xqvier.muscu.alarm;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.xqvier.muscu.DBAdapter;
import com.xqvier.muscu.Exercise;
import com.xqvier.muscu.R;
import com.xqvier.muscu.widget.ExerciseNameField;

/**
 * Activité affichant les statistiques des exercices
 * 
 * @author xMourgues
 * @version
 */
public class ExerciseStats extends Activity {
    /** Widget - Champ de recherche d'un exercice */
    private ExerciseNameField searchBar;

    /** Widget - tableau d'affichage des stats */
    private TableLayout tab;

    ArrayList<Exercise> exos;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.exercise_stat);

	tab = (TableLayout) findViewById(R.id.tabLayout);
	searchBar = (ExerciseNameField) findViewById(R.id.exerciseNameAuto);

	searchBar
	        .setOnEditorActionListener(new TextView.OnEditorActionListener() {

		    @Override
		    public boolean onEditorAction(TextView v, int actionId,
		            KeyEvent event) {
		        updateDisplay();
		        return true;
		    }
	        });
	updateDisplay();
    }

    /**
     * Met a jour l'affichage a l'écran
     */
    private void updateDisplay() {
	if (tab.getChildCount() > 2) {
	    tab.removeViews(2, tab.getChildCount() - 2);
	}
	String text = searchBar.getText().toString();
	DBAdapter db = new DBAdapter(this);
	db.open();
	if ("".equals(text)) {
	    exos = db.selectAllExercise();
	} else {
	    exos = db.selectExercise(text);
	}
	db.close();
	TableRow tr;
	TextView tv;
	if (exos == null) {
	    tr = new TableRow(this);
	    tv = new TextView(this);
	    tv.setText(R.string.error_exercise_notfound);
	    tr.addView(tv);
	    tab.addView(tr);
	} else {
	    for (int i = 0; i < exos.size(); i++) {
		Exercise exo;
		exo = exos.get(i);
		tr = new TableRow(this);
		tv = new TextView(this);
		tv.setText(exo.getName());
		tr.addView(tv);
		tv = new TextView(this);
		tv.setText(Integer.toString(exo.getTime()));
		tr.addView(tv);
		tv = new TextView(this);
		tv.setText(Integer.toString(exo.getRecovery()));
		tr.addView(tv);
		tv = new TextView(this);
		tv.setText(Integer.toString(exo.getCount()));
		tr.addView(tv);
		tab.addView(tr);
	    }
	}
    }

}
