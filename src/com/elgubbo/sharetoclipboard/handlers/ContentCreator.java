/*
 * Copyright 2012 Alexander Reichert

This file is part of ShareToClipboard.
ShareToClipboard is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
ShareToClipboard is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
You should have received a copy of the GNU General Public License along with Foobar. If not, see http://www.gnu.org/licenses/.
 */
package com.elgubbo.sharetoclipboard.handlers;

import java.util.HashMap;

import android.os.Bundle;

// TODO: Auto-generated Javadoc
/**
 * The Class ContentCreator.
 */
public abstract class ContentCreator {

	/** The extras. */
	final Bundle extras;

	/**
	 * Instantiates a new content creator.
	 * 
	 * @param extras
	 *            the extras
	 */
	ContentCreator(final Bundle extras) {
		this.extras = extras;
	}

	/**
	 * Creates the content set.
	 * 
	 * @return the hash map
	 */
	public abstract HashMap<String, String> createContentSet();
}
