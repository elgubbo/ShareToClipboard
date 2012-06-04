/*
 * Copyright 2012 Alexander Reichert

This file is part of ShareToClipboard.
ShareToClipboard is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
ShareToClipboard is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
You should have received a copy of the GNU General Public License along with Foobar. If not, see http://www.gnu.org/licenses/.
 */
package com.elgubbo.sharetoclipboard.handlers;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;

/**
 * The Class TextContentCreator.
 */
public class TextContentCreator extends ContentCreator {

	/** The content of the Text intent */
	private String content;

	/** The description of the Text intent (stored as SUBJECT in intent) */
	private String description;

	/**
	 * Instantiates a new text content creator.
	 * 
	 * @param extras
	 *            the extras
	 */
	TextContentCreator(final Bundle extras) {
		super(extras);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.elgubbo.sharetoclipboard.handlers.ContentCreator#createContentSet()
	 */
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
