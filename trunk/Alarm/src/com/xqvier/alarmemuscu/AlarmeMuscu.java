package com.xqvier.alarmemuscu;

import java.util.Timer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

public class AlarmeMuscu extends Activity {
    /** Called when the activity is first created. */
    Timer tim;
    MyTask task;
    static TextView msg;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tim = new Timer(true);
        task = new MyTask();
        MyUI vg = new MyUI(this);
        //msg = new TextView(vg.getContext());
        TextView tv = new TextView(this);
        tv.setText("Salut romain ! ");
        vg.addView(tv);
        
        //vg.addView(msg);
        setContentView(vg);
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
	super.onResume();
	//Intent in = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER ,RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL));
	Intent in = new Intent(Intent.ACTION_PICK, ContactsContract.AUTHORITY_URI);
	startActivityForResult(in, 12);
	//startActivityForResult(in, 12);
	
        //tim.schedule(task, 2000,2000);
	
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	if(resultCode == RESULT_OK && requestCode == 12) {
	    System.out.println(data.toString());
	}
    }
    
    
    
}