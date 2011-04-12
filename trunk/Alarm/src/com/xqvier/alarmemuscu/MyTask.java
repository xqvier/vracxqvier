/*
 * MyTask.java	12 avr. 2011
 * xMourgues 
 */
package com.xqvier.alarmemuscu;

import java.util.TimerTask;

/**
 * TODO Comment class
 *
 * @author xMourgues
 * @version 
 */
public class MyTask extends TimerTask {

    private static int i = 0;
    /* (non-Javadoc)
     * @see java.util.TimerTask#run()
     */
    @Override
    public void run() {
	i++;
	System.out.println(i);
    }

}
