/*
 * Musculation.java	15 avr. 2011
 * xMourgues 
 */
package com.xqvier.muscu;

import com.xqvier.muscu.alarm.AlarmConfiguration;
import com.xqvier.muscu.weight.WeightManager;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * TODO Comment class
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

	// Onglet Poids
	intent = new Intent(this, WeightManager.class);

	spec = tabHost
	        .newTabSpec("weightTab")
	        .setIndicator(res.getString(R.string.weight_name),
	                res.getDrawable(R.drawable.ic_tab_weight))
	        .setContent(intent);
	tabHost.addTab(spec);
	
	tabHost.setCurrentTab(0);
    }
}
