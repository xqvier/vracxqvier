package com.xqvier.muscu.alarm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

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
    /** Widget - Spinner pour selectionner les minutes du timer */
    private Spinner minuteTimerSpinner;

    /** Widget - Spinner pour selectionner les secondes du timer */
    private Spinner secondTimerSpinner;

    /** Widget - Spinner pour selectionner les minutes du délai */
    private Spinner minuteDelaySpinner;

    /** Widget - Spinner pour selectionner les secondes du délai */
    private Spinner secondDelaySpinner;

    /** Widget - Button pour choisir le son du beep */
    private Button selectBeepButton;

    /** Widget - Button pour demarrer le timer */
    private Button startButton;

    /** Nombre de minute pour le timer */
    private int minute = 0;

    /** Nombre de secondes pour le timer */
    private int second = 0;

    /** La sonnerie selectionnée */
    private Uri uriBeep = null;

    /** Préférences persistantes */
    SharedPreferences settings;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.alarm_configuration);

	settings = getPreferences(0);
	minuteTimerSpinner = (Spinner) findViewById(R.id.minuteTimerSpinner);
	secondTimerSpinner = (Spinner) findViewById(R.id.secondTimerSpinner);
	minuteDelaySpinner = (Spinner) findViewById(R.id.minuteDelaySpinner);
	secondDelaySpinner = (Spinner) findViewById(R.id.secondDelaySpinner);
	selectBeepButton = (Button) findViewById(R.id.selectBeepButton);
	startButton = (Button) findViewById(R.id.startButton);

	selectBeepButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		selectBeep();
	    }
	});
	startButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		start();
	    }
	});

	Integer val[] = new Integer[60];

	for (int i = 0; i < 60; i++) {
	    val[i] = i;
	}

	ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
	        android.R.layout.simple_spinner_dropdown_item, val);
	minuteTimerSpinner.setAdapter(adapter);
	secondTimerSpinner.setAdapter(adapter);
	minuteDelaySpinner.setAdapter(adapter);
	secondDelaySpinner.setAdapter(adapter);

	restoreTime();

    }

    /**
     * TODO Comment method
     * 
     */
    protected void selectBeep() {
	Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
	startActivityForResult(intent, RingtoneManager.TYPE_ALL);
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
	int minuteTimer = (Integer) this.minuteTimerSpinner.getSelectedItem();
	int secondTimer = (Integer) this.secondTimerSpinner.getSelectedItem();
	int minuteDelay = (Integer) this.minuteDelaySpinner.getSelectedItem();
	int secondDelay = (Integer) this.secondDelaySpinner.getSelectedItem();
	Toast mToast;
	if (minuteTimer == 0 && secondTimer == 0) {
	    mToast = Toast.makeText(this, R.string.error_zero,
		    Toast.LENGTH_LONG);
	    mToast.show();
	    return;

	}
	if (uriBeep == null) {
	    mToast = Toast.makeText(this, R.string.error_beep,
		    Toast.LENGTH_LONG);
	    mToast.show();
	    return;
	}
	// else
	Intent intent = new Intent(this, CountDown.class);
	int timer[] = { minuteTimer, secondTimer };
	int delay[] = {minuteDelay, secondDelay };
	intent.putExtra("com.xqvier.muscu.alarm.Start", timer);
	intent.putExtra("com.xqvier.muscu.alarm.Delay", delay);
	intent.putExtra("com.xqvier.muscu.alarm.Beep", uriBeep);
	startActivity(intent);
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
	if (resultCode == RESULT_OK && requestCode == RingtoneManager.TYPE_ALL) {
	    uriBeep = (Uri) data.getExtras()
		    .get(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
	    selectBeepButton.setText(selectBeepButton.getText() + " "
		    + RingtoneManager.getRingtone(this, uriBeep).getTitle(this));
	}
    }

}