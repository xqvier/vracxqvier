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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.xqvier.muscu.R;

/**
 * TODO Comment class
 * 
 * @author xMourgues
 * @version
 */
public class CountDown extends Activity {
    private static final short STATUS_PREPARE = 0;

    private static final short STATUS_RECOVERY = 1;

    private static final short STATUS_EXERCISE = 2;

    /** TODO Comment attribute */
    private int exercise;

    /** TODO Comment attribute */
    private int recovery;

    /** TODO Comment attribute */
    private int delay;

    /** TODO Comment attribute */
    private Ringtone beep;

    /** TODO Comment attribute */
    private TextView time;

    /** TODO Comment attribute */
    private TextView countTextView;

    /** TODO Comment attribute */
    private Button stopButton;

    /** TODO Comment attribute */
    private Button restartButton;

    /** TODO Comment attribute */
    private Button resumeButton;

    /** TODO Comment attribute */
    private ViewSwitcher switcher;

    /** TODO Comment attribute */
    private CountDownTimer timer;

    /** TODO Comment attribute */
    private long currentPos = 0;

    /** TODO Comment attribute */
    private int count = 0;

    /** TODO Comment attribute */
    private CharSequence countText;

    /** TODO Comment attribute */
    private TextView statusText;

    private boolean running;

    private short status;

    private boolean timerFinished;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.timer);

	time = (TextView) findViewById(R.id.timerText);
	countTextView = (TextView) findViewById(R.id.count);
	countText = countTextView.getText();
	stopButton = (Button) findViewById(R.id.stopButton);
	switcher = (ViewSwitcher) findViewById(R.id.buttonSwitcher);
	restartButton = (Button) findViewById(R.id.restartButton);
	resumeButton = (Button) findViewById(R.id.resumeButton);
	exercise = getIntent().getExtras().getInt(
	        "com.xqvier.muscu.alarm.Exercise");
	recovery = getIntent().getExtras().getInt(
	        "com.xqvier.muscu.alarm.Recovery");
	delay = getIntent().getExtras().getInt("com.xqvier.muscu.alarm.Delay");
	beep = RingtoneManager.getRingtone(this, (Uri) getIntent().getExtras()
	        .get("com.xqvier.muscu.alarm.Beep"));

	stopButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		stop();
	    }
	});
	restartButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		restart();

	    }
	});
	resumeButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		switcher.showNext();
		play(currentPos - 1000);

	    }
	});
	status = STATUS_PREPARE;
	timerFinished = false;
	play(delay * 1000);
	/*
	 * while(true){ if(timerFinished){ timerFinished=false; if(status ==
	 * STATUS_PREPARE || status == STATUS_RECOVERY){ status =
	 * STATUS_EXERCISE; play(exercise*1000); } else { status =
	 * STATUS_RECOVERY; play(recovery*1000); } } }
	 */
	// start();

    }

    /**
     * TODO Comment method
     * 
     */
    // private void start() {
    //
    // switcher.showNext();
    // if (minuteDelay != 0 || secondDelay != 0) {
    // count--;
    // play(1000 * ((minuteDelay * 60) + secondDelay));
    // } else {
    // play(1000 * ((minuteExercise * 60) + secondExercise));
    // }
    // }

    /**
     * TODO Comment method
     * 
     */
    private void updateDisplay() {
	time.setText(pad((currentPos / 1000) / 60) + ":"
	        + pad((currentPos / 1000) % 60));
	String text = null;
	if (status == STATUS_EXERCISE) {
	    text = getResources().getString(R.string.statusExercise);
	} else if (status == STATUS_PREPARE) {
	    text = getResources().getString(R.string.statusPrepare);
	} else {
	    text = getResources().getString(R.string.statusRecovery);
	}
	if (!running) {
	    text += " (" + getResources().getString(R.string.pause) + ")";
	}
	statusText.setText(text);
	countTextView.setText(countText + " " + Integer.toString(count));
    }

    /**
     * TODO Comment method
     * 
     * @param lo
     * 
     * @param currentPos2
     */
    protected void play(long lo) {

	timer = new CountDownTimer(lo + 1000, 1000) {

	    @Override
	    public void onTick(long millisUntilFinished) {
		currentPos = millisUntilFinished;
		updateDisplay();
	    }

	    @Override
	    public void onFinish() {

		currentPos = 0;
		count++;
		updateDisplay();
		beep.play();
		timerFinished = true;
	    }
	};
	running = true;
	timer.start();
    }

    /**
     * TODO Comment method
     * 
     * @param l
     * @return
     */
    protected String pad(long l) {
	return l < 10 ? "0" + l : Long.toString(l);
    }

    /**
     * TODO Comment method
     * 
     */
    protected void restart() {
	switcher.showNext();
	// play(1000 * ((minuteExercise * 60) + secondExercise));
    }

    /**
     * TODO Comment method
     * 
     */
    private void stop() {
	running = false;
	timer.cancel();
	switcher.showNext();
	updateDisplay();
    }

    @Override
    protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
    }
}
