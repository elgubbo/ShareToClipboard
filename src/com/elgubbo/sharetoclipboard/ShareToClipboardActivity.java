package com.elgubbo.sharetoclipboard;

import java.util.List;

import com.elgubbo.sharetoclipboarddatabase.ShareDataSource;

import greendroid.app.GDListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ShareToClipboardActivity extends GDListActivity {
	
	private ShareDataSource datasource;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarContentView(R.layout.main);
		//create the datasource responsible for maintaining ShareContent objects
		datasource = new ShareDataSource(this);
		//open the database/datasource
		datasource.open();
		List<ShareContent> values = datasource.getAllContents();
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<ShareContent> adapter = new ArrayAdapter<ShareContent>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
}