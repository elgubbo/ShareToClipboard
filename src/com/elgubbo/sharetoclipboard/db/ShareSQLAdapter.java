/*
 * Copyright 2012 Alexander Reichert

This file is part of ShareToClipboard.
ShareToClipboard is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
ShareToClipboard is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
You should have received a copy of the GNU General Public License along with Foobar. If not, see http://www.gnu.org/licenses/.
 */
package com.elgubbo.sharetoclipboard.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * The Class ShareSQLAdapter creates the DB if not existant and adds the help
 * text to the top
 */
public class ShareSQLAdapter extends SQLiteOpenHelper {

	/** The Constant TABLE_SHARED. */
	public static final String TABLE_SHARED = "shared";

	/** The Constant COLUMN_ID. */
	public static final String COLUMN_ID = "_id";

	/** The Constant COLUMN_CONTENT. */
	public static final String COLUMN_CONTENT = "content";

	/** The Constant COLUMN_DATE. */
	public static final String COLUMN_DATE = "date";

	/** The Constant COLUMN_DATATYPE. */
	public static final String COLUMN_DATATYPE = "datatype";

	/** The Constant COLUMN_DESCRIPTION. */
	public static final String COLUMN_DESCRIPTION = "description";
	// TODO add string resources
	/** The Constant HELP_CONTENT. */
	public static final String HELP_CONTENT = "help";

	/** The Constant HELP_DESCRIPTION. */
	private static final String HELP_DESCRIPTION = "help description";

	/** The Constant DATABASE_NAME. */
	private static final String DATABASE_NAME = "shared.db";

	/** The Constant DATABASE_VERSION. */
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	/** The Constant DATABASE_CREATE. */
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_SHARED + "( " + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_CONTENT
			+ " text not null, " + COLUMN_DATE + " text not null, "
			+ COLUMN_DATATYPE + " text not null, " + COLUMN_DESCRIPTION
			+ " text not null);";

	/**
	 * Instantiates a new share sql adapter.
	 * 
	 * @param context
	 *            the context
	 */
	public ShareSQLAdapter(final Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(final SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		// Add the "help" into the Table
		db.execSQL("INSERT INTO '" + TABLE_SHARED + "' VALUES ('0', '"
				+ HELP_CONTENT + "', 'value3', 'value4', '" + HELP_DESCRIPTION
				+ "')");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
			final int newVersion) {
		Log.w(ShareSQLAdapter.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHARED);
		onCreate(db);

	}

}
