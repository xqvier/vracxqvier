/*
 * Musculation.java	15 avr. 2011
 * xMourgues 
 */
package com.xqvier.muscu;

import com.xqvier.muscu.alarm.AlarmConfiguration;
import com.xqvier.muscu.alarm.ExerciseStats;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Activité maitre de l'application (point d'entrée principal)
 * 
 * @author xMourgues
 * @version
 */
public class Musculation extends TabActivity {

    /*
     * (non-Javadoc)
     * 
     * @see android.app.ActivityGroup#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);

	Resources res = getResources();
	TabHost tabHost = getTabHost();
	TabHost.TabSpec spec;
	Intent intent;

	// Onglet Alarme
	intent = new Intent(this, AlarmConfiguration.class);

	spec = tabHost
	        .newTabSpec("alarmTab")
	        .setIndicator(res.getString(R.string.alarm_name),
	                res.getDrawable(R.drawable.ic_tab_alarm))
	        .setContent(intent);
	tabHost.addTab(spec);

	// Onglet Stats
	intent = new Intent(this, ExerciseStats.class);

	spec = tabHost
	        .newTabSpec("statsTab")
	        .setIndicator(res.getString(R.string.stats),
	                res.getDrawable(R.drawable.ic_tab_stats))
	        .setContent(intent);
	tabHost.addTab(spec);
	
	tabHost.setCurrentTab(0);
    }
}
