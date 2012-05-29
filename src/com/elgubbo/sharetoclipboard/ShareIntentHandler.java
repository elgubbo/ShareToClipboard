package com.elgubbo.sharetoclipboard;

import com.elgubbo.sharetoclipboard.db.ShareDataSource;

import android.content.Intent;
import android.os.Bundle;

public class ShareIntentHandler {

	Intent intent;
	Bundle extras;
	String action;
	ShareDataSource datasource;
	String content, description;
	public ShareIntentHandler(Intent intent, ShareDataSource ds){
		this.intent = intent;
		this.extras = intent.getExtras();
		this.action = intent.getAction();
		this.datasource = ds;
	}
	
	//TODO think about how this should be implemented
	//maybe use a fancy interface or abstract class
}
