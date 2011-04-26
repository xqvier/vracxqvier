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
public class CountDownUI extends Activity {
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
    private int currentPos = 0;

    /** TODO Comment attribute */
    private int count = 0;

    /** TODO Comment attribute */
    private TextView statusText;

    private boolean running;

    private short status;

    private String statusExercise;

    private String statusPrepare;

    private String statusRecovery;

    private CountDownService timer;

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
	stopButton = (Button) findViewById(R.id.stopButton);
	switcher = (ViewSwitcher) findViewById(R.id.buttonSwitcher);
	restartButton = (Button) findViewById(R.id.restartButton);
	resumeButton = (Button) findViewById(R.id.resumeButton);
	statusText = (TextView) findViewById(R.id.status);
	exercise = getIntent().getExtras().getInt(
	        "com.xqvier.muscu.alarm.Exercise");
	recovery = getIntent().getExtras().getInt(
	        "com.xqvier.muscu.alarm.Recovery");
	delay = getIntent().getExtras().getInt("com.xqvier.muscu.alarm.Delay");
	beep = RingtoneManager.getRingtone(this, (Uri) getIntent().getExtras()
	        .get("com.xqvier.muscu.alarm.Beep"));

	statusExercise = getResources().getString(R.string.statusExercise);
	statusPrepare = getResources().getString(R.string.statusPrepare);
	statusRecovery = getResources().getString(R.string.statusRecovery);

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
	time.setText(pad(currentPos / 60) + ":" + pad(currentPos % 60));
	CharSequence text = "CA PASSE PAS DANS LES IF???";
	if (status == STATUS_EXERCISE) {
	    text = statusExercise;
	} else if (status == STATUS_PREPARE) {
	    text = statusPrepare;
	} else {
	    text = statusRecovery;
	}
	if (!running) {
	    text = text + " (" + getResources().getString(R.string.pause) + ")";
	}
	statusText.setText(text);
	countTextView.setText(getResources().getString(R.string.count) + " "
	        + Integer.toString(count));
    }

    /**
     * TODO Comment method
     * 
     * @param l
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
	 * TODO Comment constructor
	 * 
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
			count++;
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
     * TODO Comment method
     * 
     * @param currentPos2
     */
    private void play() {
	running = true;
	if (status == STATUS_PREPARE) {
	    if(delay!=0){
		timer = new CountDownService(delay);
		timer.start();
	    } else {
		status = STATUS_EXERCISE;
	    }
	}
	if (status == STATUS_EXERCISE) {
	    timer = new CountDownService(exercise);
	    timer.start();
	}
	if (status == STATUS_RECOVERY) {
	    timer = new CountDownService(recovery);
	    timer.start();
	}
    }

    /**
     * TODO Comment method
     * 
     */
    private void resume() {
	timer = new CountDownService(currentPos);
	timer.start();

    }

    /**
     * TODO Comment method
     * 
     */
    private void restart() {
	status = STATUS_PREPARE;
	play();
    }

    /**
     * TODO Comment method
     * 
     */
    private void stop() {
	running = false;
	timer.interrupt();
	updateDisplay();
    }

    @Override
    protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
    }
}
