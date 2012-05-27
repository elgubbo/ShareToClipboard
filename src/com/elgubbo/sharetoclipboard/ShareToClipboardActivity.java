package com.elgubbo.sharetoclipboard;

import greendroid.app.GDActivity;
import android.os.Bundle;

public class ShareToClipboardActivity extends GDActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarContentView(R.layout.main);
	}
}