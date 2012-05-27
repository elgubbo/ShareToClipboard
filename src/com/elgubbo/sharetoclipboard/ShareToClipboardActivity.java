package com.elgubbo.sharetoclipboard;

import java.util.List;

import com.elgubbo.sharetoclipboarddatabase.ShareDataSource;

import greendroid.app.GDListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.EventLogTags.Description;
import android.widget.ArrayAdapter;

public class ShareToClipboardActivity extends GDListActivity {
	
	private ShareDataSource datasource;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setActionBarContentView(R.layout.main);
		//create the datasource responsible for maintaining ShareContent objects
		datasource = new ShareDataSource(this);
		//open the database/datasource
		datasource.open();
		//This part consumes the Share intent
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String action = intent.getAction();

		// if this is from the share menu
		if (Intent.ACTION_SEND.equals(action)) {   if (extras.containsKey(Intent.EXTRA_TEXT)) {
		    //Use contents of Bundle
			String content, description = null;
		    content = extras.getString(Intent.EXTRA_TEXT);
		    if(extras.containsKey(Intent.EXTRA_SUBJECT)){
			    description = extras.getString(Intent.EXTRA_SUBJECT);
		    }else description = content;
		    if (content != null) {
		      datasource.createContent(content, description, "text");
		    }
		  }
		} 
		//Copy current Link/Text/Whatever to clipboard
		//A list of all Contents is created
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