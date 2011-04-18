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
    private int minuteTimer;
    private int secondTimer;
    private int minuteDelay;
    private int secondDelay;
    private Ringtone beep;
    private TextView time;
    private TextView countTextView;
    private Button stopButton;
    private Button restartButton;
    private Button resumeButton;
    private ViewSwitcher switcher;
    private CountDownTimer timer;
    private long currentPos = 0;
    private int count = 0;
    private CharSequence countText;

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
	minuteTimer = getIntent().getExtras().getIntArray(
	        "com.xqvier.muscu.alarm.Start")[0];
	secondTimer = getIntent().getExtras().getIntArray(
	        "com.xqvier.muscu.alarm.Start")[1];
	minuteDelay = getIntent().getExtras().getIntArray(
	        "com.xqvier.muscu.alarm.Delay")[0];
	secondDelay = getIntent().getExtras().getIntArray(
	        "com.xqvier.muscu.alarm.Delay")[1];
	beep = RingtoneManager.getRingtone(this, (Uri) getIntent().getExtras().get("com.xqvier.muscu.alarm.Beep"));

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
	countTextView.setText(countText + " " + Integer.toString(count));
	start();

    }

    /**
     * TODO Comment method
     *
     */
    private void start() {

	switcher.showNext();
	if(minuteDelay != 0 || secondDelay != 0){
	    count--;
	    play(1000*((minuteDelay * 60) + secondDelay));
	} else {
	    play(1000 * ((minuteTimer * 60) + secondTimer));
	}
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
		time.setText(pad((millisUntilFinished / 1000) / 60) + ":"
		        + pad((millisUntilFinished / 1000) % 60));
		currentPos = millisUntilFinished;
	    }

	    
	    @Override
	    public void onFinish() {
		
		time.setText("00:00");
		beep.play();
		count++;
		countTextView.setText(countText + " " + Integer.toString(count));
		play(1000 * ((minuteTimer * 60) + secondTimer));
	    }
	};
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
	play(1000 * ((minuteTimer * 60) + secondTimer));
    }

    /**
     * TODO Comment method
     * 
     */
    private void stop() {
	timer.cancel();
	switcher.showNext();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        
    }
}
