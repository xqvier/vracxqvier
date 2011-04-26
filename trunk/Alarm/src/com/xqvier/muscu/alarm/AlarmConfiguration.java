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

    /** Widget - Spinner pour selectionner les minutes du délai */
    private Spinner minuteDelaySpinner;

    /** Widget - Spinner pour selectionner les secondes du délai */
    private Spinner secondDelaySpinner;

    /** Widget - Button pour choisir le son du beep */
    private Button selectBeepButton;

    /** Widget - Button pour demarrer le timer */
    private Button startButton;

    /** Nombre de secondes pour le delai de préparation */
    private int delay;

    /** Nombre de secondes pour le delai de recupération */
    private int recovery;
    
    /** Nombre de secondes pour le timer d'exercice */
    private int exercise;
    
    /** La sonnerie selectionnée */
    private Uri uriBeep = null;
    
    /** Les données persistentes */
    SharedPreferences settings;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.alarm_configuration);
	settings = getPreferences(MODE_PRIVATE);
	
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
    private void start() {
	saveTime();
	

	Toast mToast;
	if (exercise == 0 || recovery == 0) {
	    mToast = Toast.makeText(
		    this,
		    getResources().getString(R.string.error_zero,
		            getResources().getString(R.string.exercise),
		            getResources().getString(R.string.recovery)),
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
	Intent intent = new Intent(this, CountDownUI.class);
	intent.putExtra("com.xqvier.muscu.alarm.Exercise", exercise);
	intent.putExtra("com.xqvier.muscu.alarm.Recovery", recovery);
	intent.putExtra("com.xqvier.muscu.alarm.Delay", delay);
	intent.putExtra("com.xqvier.muscu.alarm.Beep", uriBeep);
	startActivity(intent);
    }
    /* (non-Javadoc)
     * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

    /**
     * TODO Comment method
     * 
     */
    private void saveTime() {
	
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

	exercise = (minuteExercise * 60) + secondExercise;
	recovery = (minuteRecovery * 60) + secondRecovery;
	delay = (minuteDelay * 60) + secondDelay;
	
	SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
	editor.putInt("delay",delay);
	editor.putInt("exercise", exercise);
	editor.putInt("recovery", recovery);
	editor.putString("ringtone", uriBeep.toString());
	editor.commit();
	/*try {
	    FileOutputStream fos = openFileOutput(settingsFile, MODE_PRIVATE);
	    fos.write(delay);
	    fos.write(recovery);
	    fos.write(exercise);
	    
        } catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
        } catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
        }*/
        /*settings.edit().putInt("minute", this.minute);
        settings.edit().putInt("second", this.second);
        settings.edit().commit();*/
    }

    /**
     * TODO Comment method
     * 
     */
    private void restoreTime() {
	delay = settings.getInt("delay", 0);
	recovery = settings.getInt("recovery", 0);
	exercise = settings.getInt("exercise", 0);
	uriBeep = Uri.parse(settings.getString("ringtone", ""));
	System.out.println(delay + " " + recovery + " " + exercise + " " + uriBeep);
	minuteExerciseSpinner.setSelection(exercise/60);
	secondExerciseSpinner.setSelection(exercise%60);
	minuteRecoverySpinner.setSelection(recovery/60);
	secondRecoverySpinner.setSelection(recovery%60);
	minuteDelaySpinner.setSelection(delay/60);
	secondDelaySpinner.setSelection(delay%60);
	
	selectBeepButton
	    .setText(selectBeepButton.getText()
	            + " : "
	            + RingtoneManager.getRingtone(this, uriBeep)
	                    .getTitle(this));
	
	
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
		            + " : "
		            + RingtoneManager.getRingtone(this, uriBeep)
		                    .getTitle(this));
	}
    }

}