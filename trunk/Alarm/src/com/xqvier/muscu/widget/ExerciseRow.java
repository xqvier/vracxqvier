/*
 * ExerciseRow.java	29 avr. 2011
 * xMourgues 
 */
package com.xqvier.muscu.widget;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.xqvier.muscu.R;

/**
 * TODO Comment class
 * 
 * @author xMourgues
 * @version
 */
public class ExerciseRow extends TableRow {

    private boolean deleting = false;
    GestureDetector gd;
    private Button deleteButton;

    /**
     * @return the deleteButton
     */
    public Button getDeleteButton() {
	return deleteButton;
    }

    public int getExerciseId() {
	TextView tv = (TextView) this.findViewWithTag("tag");
	if(tv == null){
	    return -1;
	}
	return Integer.parseInt(tv.getText().toString());
    }

    /**
     * TODO Comment constructor
     * 
     * @param context
     */
    public ExerciseRow(Context context) {
	super(context);
	this.setFocusableInTouchMode(true);
	this.gd = new GestureDetector(new MyGestureDetector());
	deleteButton = new Button(context);
	deleteButton.setText(R.string.delete_button);
	deleteButton.setVisibility(View.GONE);
	deleteButton.setHeight(this.getMeasuredHeight());
	this.setOnLongClickListener(new View.OnLongClickListener() {

	    @Override
	    public boolean onLongClick(View v) {
		if (deleting) {
		    deleting = false;
		    clearDeleteProposal();
		} else {
		    deleting = true;
		    deleteProposal();
		}
		return true;
	    }
	});
	// this.setOnTouchListener(new View.OnTouchListener() {
	//
	// @Override
	// public boolean onTouch(View v, MotionEvent event) {
	// System.out.println("touched");
	// return gd.onTouchEvent(event);
	// }
	// });
    }

    private class MyGestureDetector extends SimpleOnGestureListener {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.SimpleOnGestureListener#onFling(android
	 * .view.MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
	        float velocityY) {
	    System.out.println("ExerciseRow.MyGestureDetector.onFling()");
	    if (deleting) {
		deleting = false;
		clearDeleteProposal();
	    } else {
		deleting = true;
		deleteProposal();
	    }
	    return true;
	}

    }

    /**
     * TODO Comment method
     * 
     */
    private void clearDeleteProposal() {
	System.out.println("disparition du bouton");
	deleteButton.setVisibility(View.GONE);
    }

    /**
     * TODO Comment method
     * 
     */
    private void deleteProposal() {
	System.out.println("affichage du bouton");
	deleteButton.setVisibility(View.VISIBLE);
    }
}
