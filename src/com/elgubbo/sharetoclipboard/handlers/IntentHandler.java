/*
 * Copyright 2012 Alexander Reichert

This file is part of ShareToClipboard.

ShareToClipboard is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

ShareToClipboard is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with Foobar. If not, see http://www.gnu.org/licenses/.
 */
package com.elgubbo.sharetoclipboard.handlers;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.widget.Toast;

import com.elgubbo.sharetoclipboard.db.ShareDataSource;

/**
 * The Class IntentHandler decides how the Intent should be handled e.g. Uses
 * TextContentCreator to handle a Text sharing Intent
 */
public class IntentHandler {

	/** The intent. */
	private final Intent intent;

	/** The datasource where data is written to. */
	private final ShareDataSource datasource;

	/** The content. */
	private String content;

	/** The description. */
	private String description;

	/** The data type. */
	private String dataType;

	/** The cc. */
	private ContentCreator cc;

	/** The context. */
	private final Context context;

	/** The clip man. */
	private final ClipboardManager clipMan;

	/**
	 * Instantiates a new intent handler.
	 * 
	 * @param i
	 *            the intent to handle
	 * @param ds
	 *            the datasource that can be written to
	 * @param cont
	 *            the context of the application
	 * @param cm
	 *            the ClipBoardManager to copy the link to
	 */
	public IntentHandler(final Intent i, final ShareDataSource ds,
			final Context cont, final ClipboardManager cm) {
		this.intent = i;
		this.datasource = ds;
		this.context = cont;
		this.clipMan = cm;
	}

	/**
	 * Handle intent.
	 */
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
