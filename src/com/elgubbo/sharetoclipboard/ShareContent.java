/*
 * Copyright 2012 Alexander Reichert

This file is part of ShareToClipboard.
ShareToClipboard is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
ShareToClipboard is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
You should have received a copy of the GNU General Public License along with Foobar. If not, see http://www.gnu.org/licenses/.
 */
package com.elgubbo.sharetoclipboard;

import android.text.format.Time;

/**
 * The Class ShareContent. It describes on set of data that has been shared.
 */
public class ShareContent {

	// content can eventually be changed to an abstract content class if media
	// should be supported
	/** The content of the share */
	private String content;

	/** The id. */
	private long id;

	/** The data type. */
	private String dataType;

	/** The time when this was stored */
	private Time time;

	/** The description of the Content (often from Intent.SUBJECT */
	private String description;

	/**
	 * Gets the content.
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Gets the data type.
	 * 
	 * @return the data type
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the time.
	 * 
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * Sets the content.
	 * 
	 * @param content
	 *            the new content
	 */
	public void setContent(final String content) {
		this.content = content;
	}

	/**
	 * Sets the data type.
	 * 
	 * @param dataType
	 *            the new data type
	 */
	public void setDataType(final String dataType) {
		this.dataType = dataType;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 *            the new description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * Sets the time.
	 * 
	 * @param time
	 *            the new time
	 */
	public void setTime(final Time time) {
		this.time = time;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return description;
	}

}
