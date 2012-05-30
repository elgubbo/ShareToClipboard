package com.elgubbo.sharetoclipboard.listeners;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ViewFlipper;

public class OnItemTouchListener implements OnTouchListener {

	static ViewFlipper currentlyFlipped;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		ViewFlipper f = (ViewFlipper) v;
		f.showNext();
		//Make sure only one ListItem is currently flipped
		//TODO check feedback what way is better
		if (currentlyFlipped != null && currentlyFlipped.getCurrentView().equals(
						currentlyFlipped.getChildAt(1)) && currentlyFlipped != f){
			currentlyFlipped.showNext();
		}
		currentlyFlipped = f;
		return false;
	}

}
