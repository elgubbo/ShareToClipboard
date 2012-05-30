package com.elgubbo.sharetoclipboard.handlers;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.widget.Toast;

import com.elgubbo.sharetoclipboard.db.ShareDataSource;

public class IntentHandler {

	private final Intent intent;
	private final ShareDataSource datasource;
	private String content;
	private String description;
	private String dataType;
	private ContentCreator cc;
	private final Context context;
	private final ClipboardManager clipMan;

	public IntentHandler(final Intent i, final ShareDataSource ds,
			final Context cont, final ClipboardManager cm) {
		this.intent = i;
		this.datasource = ds;
		this.context = cont;
		this.clipMan = cm;
	}

	public void handleIntent() {
		final String action = intent.getAction();
		final Bundle extras = intent.getExtras();
		HashMap<String, String> contentMap = null;
		if (action.equals(Intent.ACTION_SEND)) {
			if (extras.containsKey(Intent.EXTRA_TEXT)) {
				cc = new TextContentCreator(extras);
				contentMap = cc.createContentSet();
			}
			// TODO add support for more Intent.EXTRAS

			// Get the content and description
			this.content = contentMap.get("content");
			this.description = contentMap.get("description");
			this.dataType = contentMap.get("type");
			// write to database
			datasource.createContent(content, description, dataType);
			// Copy current Link/Text/Whatever to clipboard
			clipMan.setText(content);
			// TODO add string resource
			final Toast t = Toast.makeText(context, "Added share to clipboard",
					3000);
			t.show();
		}
	}
}
