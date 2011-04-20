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
    private Spinner minuteExerciseSpinner;

    /** Widget - Spinner pour selectionner les secondes du timer */
    private Spinner secondExerciseSpinner;

    /** Widget - Spinner pour selectionner les minutes du timer */
    private Spinner minuteRecoverySpinner;

    /** Widget - Spinner pour selectionner les secondes du timer */
    private Spinner secondRecoverySpinner;

    /** Widget - Spinner pour selectionner les minutes du d�lai */
    private Spinner minuteDelaySpinner;

    /** Widget - Spinner pour selectionner les secondes du d�lai */
    private Spinner secondDelaySpinner;

    /** Widget - Button pour choisir le son du beep */
    private Button selectBeepButton;

    /** Widget - Button pour demarrer le timer */
    private Button startButton;

    /** Nombre de minute pour le timer */
    private int minute = 0;

    /** Nombre de secondes pour le timer */
    private int second = 0;

    /** La sonnerie selectionn�e */
    private Uri uriBeep = null;

    /** Pr�f�rences persistantes */
    SharedPreferences settings;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.alarm_configuration);

	settings = getPreferences(0);
	minuteExerciseSpinner = (Spinner) findViewById(R.id.minuteExerciseSpinner);
	secondExerciseSpinner = (Spinner) findViewById(R.id.secondExerciseSpinner);
	minuteRecoverySpinner = (Spinner) findViewById(R.id.minuteRecoverySpinner);
	secondRecoverySpinner = (Spinner) findViewById(R.id.secondRecoverySpinner);
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
	minuteExerciseSpinner.setAdapter(adapter);
	secondExerciseSpinner.setAdapter(adapter);
	minuteRecoverySpinner.setAdapter(adapter);
	secondRecoverySpinner.setAdapter(adapter);
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
	int minuteExercise = (Integer) this.minuteExerciseSpinner
	        .getSelectedItem();
	int secondExercise = (Integer) this.secondExerciseSpinner
	        .getSelectedItem();
	int minuteRecovery = (Integer) this.minuteRecoverySpinner
	        .getSelectedItem();
	int secondRecovery = (Integer) this.secondRecoverySpinner
	        .getSelectedItem();
	int minuteDelay = (Integer) this.minuteDelaySpinner.getSelectedItem();
	int secondDelay = (Integer) this.secondDelaySpinner.getSelectedItem();

	int exercise = (minuteExercise*60)+ secondExercise;
	int recovery = (minuteRecovery*60)+ secondRecovery;
	int delay = (minuteDelay*60)+ secondDelay;
	
	Toast mToast;
	if (exercise == 0 || recovery == 0) {
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
	intent.putExtra("com.xqvier.muscu.alarm.Exercise", exercise);
	intent.putExtra("com.xqvier.muscu.alarm.Recovery", recovery);
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
	    uriBeep = (Uri) data.getExtras().get(
		    RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
	    selectBeepButton
		    .setText(selectBeepButton.getText()
		            + " "
		            + RingtoneManager.getRingtone(this, uriBeep)
		                    .getTitle(this));
	}
    }

}