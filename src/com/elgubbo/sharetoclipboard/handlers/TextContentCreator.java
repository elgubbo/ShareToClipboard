package com.elgubbo.sharetoclipboard.handlers;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;

public class TextContentCreator extends ContentCreator {

	private String content;
	private String description;

	TextContentCreator(final Bundle extras) {
		super(extras);
	}

	@Override
	public HashMap<String, String> createContentSet() {
		// Use contents of Bundle
		final HashMap<String, String> contentMap = new HashMap<String, String>();
		content = extras.getString(Intent.EXTRA_TEXT);
		if (extras.containsKey(Intent.EXTRA_SUBJECT)) {
			description = extras.getString(Intent.EXTRA_SUBJECT);
			contentMap.put("description", description);
		} else
			description = content;
		if (content != null) {
			contentMap.put("content", content);
		}

		contentMap.put("type", "text");
		return contentMap;
	}

}
