package com.xqvier.muscu.alarm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.xqvier.muscu.R;

/**
 * TODO Comment class
 *
 * @author xMourgues
 * @version 
 */
/**
 * TODO Comment class
 * 
 * @author xMourgues
 * @version
 */
public class AlarmConfiguration extends Activity {
    /** Widget - Spinner pour le défilements des minute */
    private Spinner minuteSpinner;

    /** Widget - Spinner pour le défilements des secondes */
    private Spinner secondSpinner;

    /** Widget - Button pour demarrer le timer */
    private Button startButton;

    /** Nombre de minute pour le timer */
    private int minute;

    /** Nombre de secondes pour le timer */
    private int second;

    /** Préférences persistantes */
    SharedPreferences settings;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.alarm_configuration);

	settings = getPreferences(0);
	minuteSpinner = (Spinner) findViewById(R.id.minuteSpinner);
	secondSpinner = (Spinner) findViewById(R.id.secondSpinner);
	startButton = (Button) findViewById(R.id.startButton);

	Integer val[] = new Integer[60];

	for (int i = 0; i < 60; i++) {
	    val[i] = i;
	}

	ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
	        android.R.layout.simple_spinner_dropdown_item, val);
	minuteSpinner.setAdapter(adapter);
	secondSpinner.setAdapter(adapter);

	startButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		start();
	    }
	});
	restoreTime();
	updateDisplay();

    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
	super.onDestroy();
	saveTime();
    }

    /**
     * TODO Comment method
     * 
     */
    private void saveTime() {
	settings.edit().putInt("minute", this.minute);
	settings.edit().putInt("second", this.second);
	settings.edit().commit();
    }

    /**
     * TODO Comment method
     * 
     */
    protected void start() {
	Intent intent = new Intent(this, CountDown.class);
	int data[] = { (Integer) this.minuteSpinner.getSelectedItem(),
	        (Integer) this.secondSpinner.getSelectedItem() };
	intent.putExtra("com.xqvier.muscu.alarm.Start", data);
	startActivity(intent);
    }

    /**
     * TODO Comment method
     * 
     */
    private void updateDisplay() {
	minuteSpinner.setSelection(minute, true);
	secondSpinner.setSelection(second, true);
    }

    /**
     * TODO Comment method
     * 
     */
    private void restoreTime() {
	this.minute = settings.getInt("minute", 0);
	this.second = settings.getInt("second", 0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onActivityResult(int, int,
     * android.content.Intent)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	if (resultCode == RESULT_OK && requestCode == 12) {
	    System.out.println(data.toString());
	}
    }

}