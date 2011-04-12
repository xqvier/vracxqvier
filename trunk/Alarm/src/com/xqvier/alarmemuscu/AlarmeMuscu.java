package com.xqvier.alarmemuscu;

import java.util.Timer;
import android.app.Activity;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.widget.TextView;

public class AlarmeMuscu extends Activity {
    /** Called when the activity is first created. */
    Timer tim;
    MyTask task;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tim = new Timer(true);
        task = new MyTask();
        TextView tv = new TextView(this);
        
        tv.setText("Salut romain ! ");        
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
	super.onResume();
	Intent in = new Intent(Intent.ACTION_PICK, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
	in.setAction(RingtoneManager.ACTION_RINGTONE_PICKER);
	startActivityForResult(in, 12);
        tim.schedule(task, 2000,2000);
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	if(resultCode == Activity.RESULT_OK && requestCode == 12) {
	    System.out.println(data.toString());
	}
    }
    
    
    
}