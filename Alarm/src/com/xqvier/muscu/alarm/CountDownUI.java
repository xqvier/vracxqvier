/*
 * Timer.java	15 avr. 2011
 * xMourgues 
 */
package com.xqvier.muscu.alarm;

import android.app.Activity;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.xqvier.muscu.DBAdapter;
import com.xqvier.muscu.Exercise;
import com.xqvier.muscu.R;

/**
 * Activité pour l'affichage du compte a rebours
 * 
 * @author xMourgues
 * @version
 */
public class CountDownUI extends Activity {
    private static final short STATUS_PREPARE = 0;

    private static final short STATUS_RECOVERY = 1;

    private static final short STATUS_EXERCISE = 2;

    /** Objet d'exercice en cours */
    Exercise exo;

    /** la sonnerie utilisée entre les timers */
    private Ringtone beep;

    /** Widget - Texte de représentation du défilement du temps */
    private TextView time;

    /** Widget - Text de réprésentation du nombre d'exercice effectué */
    private TextView countTextView;

    /** Widget - Bouton d'arret du timer */
    private Button stopButton;

    /** Widget - Bouton de redémarrage du timer */
    private Button restartButton;

    /** Widget - Bouton de reprise du timer */
    private Button resumeButton;

    /**
     * Widget - Switcher permettant de switcher entre les boutons (stop et
     * restart/resume)
     */
    private ViewSwitcher switcher;

    /** Position du timer actuelle pour la reprise */
    private int currentPos = 0;

    /**
     * Widget - Text représentant le status du timer (pause, preparation,
     * recuperation, exercice en cours)
     */
    private TextView statusText;

    /** booleen indiquant si le timer est en pause ou non */
    private boolean running;

    /** indicateur de status du timer (preparation, recuperation, exercice) */
    private short status;

    /** Thread du timer */
    private CountDownService timer;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.timer);

	time = (TextView) findViewById(R.id.timerText);
	countTextView = (TextView) findViewById(R.id.count);
	stopButton = (Button) findViewById(R.id.stopButton);
	switcher = (ViewSwitcher) findViewById(R.id.buttonSwitcher);
	restartButton = (Button) findViewById(R.id.restartButton);
	resumeButton = (Button) findViewById(R.id.resumeButton);
	statusText = (TextView) findViewById(R.id.status);

	exo = (Exercise) getIntent().getExtras().get(
	        "com.xqvier.muscu.alarm.Exercise");
	Log.d("CountDown", exo.toString());
	beep = RingtoneManager.getRingtone(this, (Uri) getIntent().getExtras()
	        .get("com.xqvier.muscu.alarm.Beep"));

	stopButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		switcher.showNext();
		stop();
	    }
	});
	restartButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		switcher.showNext();
		restart();

	    }
	});
	resumeButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		switcher.showNext();
		resume();

	    }
	});
	status = STATUS_PREPARE;
	play();

    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
	super.onDestroy();
	DBAdapter db = new DBAdapter(this);
	db.open();
	db.insertExercise(exo);
	db.close();
    }

    /**
     * Mise a jour de l'affichage dynamique
     */
    private void updateDisplay() {
	time.setText(pad(currentPos / 60) + ":" + pad(currentPos % 60));
	CharSequence text;
	if (status == STATUS_EXERCISE) {
	    text = getResources().getString(R.string.statusExercise);
	} else if (status == STATUS_PREPARE) {
	    text = getResources().getString(R.string.statusPrepare);
	} else {
	    text = getResources().getString(R.string.statusRecovery);
	}
	if (!running) {
	    text = text + " (" + getResources().getString(R.string.pause) + ")";
	}
	statusText.setText(text);
	countTextView.setText(getResources().getString(R.string.count) + " "
	        + Integer.toString(exo.getCount()));
    }

    /**
     * rajoute un zero devant un nombre à un chiffre pour le mettre sur 2
     * chiffre pour l'affichage
     * 
     * @param l
     *            le nombre à "pader"
     * @return
     */
    private String pad(long l) {
	return l < 10 ? "0" + l : Long.toString(l);
    }

    private class CountDownService extends Thread {
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#interrupt()
	 */
	@Override
	public void interrupt() {
	    timer.cancel();
	    super.interrupt();
	}

	private CountDownTimer timer;

	/**
	 * Constructeur du Thread de compte à rebours
	 * 
	 * @param sec
	 *            le nombre de seconde à décompter
	 */
	public CountDownService(int sec) {
	    timer = new CountDownTimer(sec * 1000, 1000) {

		@Override
		public void onTick(long millisUntilFinished) {
		    currentPos = (int) (millisUntilFinished / 1000);
		    updateDisplay();
		}

		@Override
		public void onFinish() {

		    currentPos = 0;
		    if (status == STATUS_PREPARE || status == STATUS_RECOVERY) {
			status = STATUS_EXERCISE;
		    } else {
			status = STATUS_RECOVERY;
			exo.incrementCount();
		    }

		    beep.play();
		    play();
		    updateDisplay();
		}
	    };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
	    timer.start();
	}

    }

    /**
     * Lance le thread qu'il faut pour le compte a rebours
     */
    private void play() {
	running = true;
	if (status == STATUS_PREPARE) {
	    if (exo.getDelay() != 0) {
		timer = new CountDownService(exo.getDelay());
		timer.start();
	    } else {
		status = STATUS_EXERCISE;
	    }
	}
	if (status == STATUS_EXERCISE) {
	    timer = new CountDownService(exo.getTime());
	    timer.start();
	}
	if (status == STATUS_RECOVERY) {
	    timer = new CountDownService(exo.getRecovery());
	    timer.start();
	}
    }

    /**
     * Relance le compte a rebours de l'endroit ou il a été stopé
     * 
     */
    private void resume() {
	running = true;
	timer = new CountDownService(currentPos);
	timer.start();

    }

    /**
     * Relance le compte a rebours depuis le début (délai compris)
     * 
     */
    private void restart() {
	status = STATUS_PREPARE;
	play();
    }

    /**
     * Arrete le compte a rebours
     */
    private void stop() {
	running = false;
	timer.interrupt();
	updateDisplay();
    }
}
