package com.elgubbo.sharetoclipboarddatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ShareSQLAdapter extends SQLiteOpenHelper{

	public static final String TABLE_SHARED = "shared";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_CONTENT = "content";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_DATATYPE = "datatype"; 
	public static final String COLUMN_DESCRIPTION = "description";

	private static final String DATABASE_NAME = "shared.db";
	private static final int DATABASE_VERSION = 1;
	
	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_SHARED + "( " + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_CONTENT
			+ " text not null, " + COLUMN_DATE
			+ " text not null, " + COLUMN_DATATYPE
			+ " text not null, " + COLUMN_DESCRIPTION
			+ " text not null);";
	
	public ShareSQLAdapter(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(ShareSQLAdapter.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHARED);
		onCreate(db);
		
	}

}
