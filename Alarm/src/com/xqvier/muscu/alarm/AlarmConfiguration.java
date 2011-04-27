package com.xqvier.muscu.alarm;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.xqvier.muscu.DBAdapter;
import com.xqvier.muscu.Exercise;
import com.xqvier.muscu.R;
import com.xqvier.muscu.widget.ExerciseNameField;

/**
 * Activité de configuration des comptes a rebours
 * 
 * @author xMourgues
 * @version
 */
public class AlarmConfiguration extends Activity {
    /** Widget - Champ de texte pour le nom de l'exercice */
    private ExerciseNameField nameField;

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

    /** Widget - Button pour afficher les stats */
    private Button statButton;

    /** Exercice en configuration */
    private Exercise exo;

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
	exo = new Exercise();

	nameField = (ExerciseNameField) findViewById(R.id.exerciseNameAuto);
	minuteExerciseSpinner = (Spinner) findViewById(R.id.minuteExerciseSpinner);
	secondExerciseSpinner = (Spinner) findViewById(R.id.secondExerciseSpinner);
	minuteRecoverySpinner = (Spinner) findViewById(R.id.minuteRecoverySpinner);
	secondRecoverySpinner = (Spinner) findViewById(R.id.secondRecoverySpinner);
	minuteDelaySpinner = (Spinner) findViewById(R.id.minuteDelaySpinner);
	secondDelaySpinner = (Spinner) findViewById(R.id.secondDelaySpinner);
	selectBeepButton = (Button) findViewById(R.id.selectBeepButton);
	startButton = (Button) findViewById(R.id.startButton);
	statButton = (Button) findViewById(R.id.alarmStatButton);

	nameField
	        .setOnEditorActionListener(new TextView.OnEditorActionListener() {

		    @Override
		    public boolean onEditorAction(TextView v, int actionId,
		            KeyEvent event) {
		        updateExercise();
		        updateDisplay();
		        return true;
		    }
	        });
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
	statButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		show_stat();
	    }
	});

	Integer val[] = new Integer[60];

	for (int i = 0; i < 60; i++) {
	    val[i] = i;
	}

	ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
	        android.R.layout.simple_dropdown_item_1line, val);
	minuteExerciseSpinner.setAdapter(adapter);
	secondExerciseSpinner.setAdapter(adapter);
	minuteRecoverySpinner.setAdapter(adapter);
	secondRecoverySpinner.setAdapter(adapter);
	minuteDelaySpinner.setAdapter(adapter);
	secondDelaySpinner.setAdapter(adapter);
	restoreConf();

    }

    /**
     * Met a jour l'exercice courant avec le dernier exercice dont le nom est
     * inscris dans la barre de nom
     */
    private void updateExercise() {
	if (!"".equals(nameField.getText())) {
	    DBAdapter db = new DBAdapter(this);
	    db.open();
	    Exercise temp = db.selectLastExercise(nameField.getText()
		    .toString());
	    if (temp != null) {
		exo = temp;
	    }
	    db.close();
	}
    }

    /**
     * Met à jour l'affichage de l'activité
     */
    private void updateDisplay() {

	nameField.setText(exo.getName());
	minuteExerciseSpinner.setSelection(exo.getTime() / 60);
	secondExerciseSpinner.setSelection(exo.getTime() % 60);
	minuteRecoverySpinner.setSelection(exo.getRecovery() / 60);
	secondRecoverySpinner.setSelection(exo.getRecovery() % 60);
	minuteDelaySpinner.setSelection(exo.getDelay() / 60);
	secondDelaySpinner.setSelection(exo.getDelay() % 60);

	selectBeepButton.setText(getResources().getString(
	        R.string.select_beep_button)
	        + " : "
	        + RingtoneManager.getRingtone(this, uriBeep).getTitle(this));
    }

    /**
     * Lance l'activité pour montrer les stats
     */
    private void show_stat() {
	Intent intent = new Intent(this, ExerciseStats.class);
	startActivity(intent);
    }

    /**
     * méthode pour la séléction de la sonnerie déclenchée entre chaque timer
     */
    private void selectBeep() {
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
	saveConf();
    }

    /**
     * Lancement de l'activité de compte a rebours. Vérifie que les parametres
     * soit fournis avant lancement
     */
    private void start() {
	saveConf();

	Toast mToast;
	if (exo.getTime() == 0 || exo.getRecovery() == 0) {
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
	exo.setDate(Calendar.getInstance().getTime());

	Log.d("alarmConf", exo.toString());
	intent.putExtra("com.xqvier.muscu.alarm.Exercise", exo);
	intent.putExtra("com.xqvier.muscu.alarm.Beep", uriBeep);
	startActivity(intent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

    /**
     * Sauvegarde les données entrée pour la configuration du timer de manière
     * persistante
     */
    private void saveConf() {
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

	exo.setName(nameField.getText().toString());
	exo.setTime((minuteExercise * 60) + secondExercise);
	exo.setRecovery((minuteRecovery * 60) + secondRecovery);
	exo.setDelay((minuteDelay * 60) + secondDelay);

	SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
	editor.putString("name", exo.getName());
	editor.putInt("exercise", exo.getTime());
	editor.putInt("recovery", exo.getRecovery());
	editor.putInt("delay", exo.getDelay());
	editor.putString("ringtone", uriBeep.toString());
	editor.commit();
    }

    /**
     * Restaure les données persistantes pour la configuration des timers
     */
    private void restoreConf() {
	exo.setName(settings.getString("name", ""));
	exo.setTime(settings.getInt("exercise", 0));
	exo.setRecovery(settings.getInt("recovery", 0));
	exo.setDelay(settings.getInt("delay", 0));
	uriBeep = Uri.parse(settings.getString("ringtone", ""));
	updateDisplay();
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