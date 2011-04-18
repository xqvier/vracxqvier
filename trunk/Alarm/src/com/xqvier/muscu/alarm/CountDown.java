/*
 * Timer.java	15 avr. 2011
 * xMourgues 
 */
package com.xqvier.muscu.alarm;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xqvier.muscu.R;

/**
 * TODO Comment class
 * 
 * @author xMourgues
 * @version
 */
public class CountDown extends Activity {
    private int minute;
    private int second;
    private Boolean keepOn = true;
    private TextView time;
    private Button stopButton;
    private boolean timerFinished = false;

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
	stopButton = (Button) findViewById(R.id.cancelButton);

	stopButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		stop();
	    }
	});
	minute = getIntent().getExtras().getIntArray(
	        "com.xqvier.muscu.alarm.Start")[0];
	second = getIntent().getExtras().getIntArray(
	        "com.xqvier.muscu.alarm.Start")[1];
	System.out.println(minute+", "+second);
	CountDownTimer timer = new CountDownTimer(1000*(minute*60+second), 1000) {
	    public void onTick(long millisUntilFinished) {
		if(millisUntilFinished < 1000){
		    time.setText(pad(0)+":"+pad(0));
		} else {
		    time.setText(pad((millisUntilFinished/1000)/60)+":"+pad((millisUntilFinished/1000)%60));
		}
	    }

	    public void onFinish() {
		timerFinished = true;
	    }
	};
	while(keepOn){
	    if(timerFinished ){
		timer.start();
	    }
	}

    }

    /**
     * TODO Comment method
     *
     */
    private void stop() {
	keepOn = false;
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    /**
     * TODO Comment method
     * 
     * @param second2
     * @return
     */
    private String pad(long l) {
	return l < 10 ? "0" + l : Long.toString(l);
    }
}
