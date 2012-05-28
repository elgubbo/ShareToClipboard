package com.elgubbo.sharetoclipboarddatabase;

import java.util.ArrayList;
import java.util.List;

import com.elgubbo.sharetoclipboard.ShareContent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;
import android.util.Log;

public class ShareDataSource {

	private SQLiteDatabase database;
	private ShareSQLAdapter dbHelper;
	private String[] allColumns = { ShareSQLAdapter.COLUMN_ID,
			ShareSQLAdapter.COLUMN_CONTENT,
			ShareSQLAdapter.COLUMN_DATATYPE,
			ShareSQLAdapter.COLUMN_DATE,
			ShareSQLAdapter.COLUMN_DESCRIPTION};
	public ShareDataSource(Context context) {
		dbHelper = new ShareSQLAdapter(context);
	}

	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public ShareContent createContent(String content,String desctription, String dataType){
		Time currentTime = new Time(Time.getCurrentTimezone());
		ContentValues values = new ContentValues();
		values.put(ShareSQLAdapter.COLUMN_CONTENT, content);
		values.put(ShareSQLAdapter.COLUMN_DATATYPE, dataType);
		values.put(ShareSQLAdapter.COLUMN_DATE, currentTime.toString());
		values.put(ShareSQLAdapter.COLUMN_DESCRIPTION, desctription);
		long insertId = database.insert(ShareSQLAdapter.TABLE_SHARED, null,
				values);
		Cursor cursor = database.query(ShareSQLAdapter.TABLE_SHARED,
				allColumns, ShareSQLAdapter.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		ShareContent newContent = cursorToContent(cursor);
		cursor.close();
		return newContent;
	}
	
	public void deleteContent(ShareContent content){
		long id = content.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(ShareSQLAdapter.TABLE_SHARED, ShareSQLAdapter.COLUMN_ID
				+ " = " + id, null);
	}
	

	public List<ShareContent> getAllContents() {
		List<ShareContent> contents = new ArrayList<ShareContent>();

		Cursor cursor = database.query(ShareSQLAdapter.TABLE_SHARED,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			ShareContent content = cursorToContent(cursor);
			contents.add(content);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return contents;
	}
	private ShareContent cursorToContent(Cursor cursor) {
		ShareContent content = new ShareContent();
		content.setId(cursor.getLong(0));
		content.setContent(cursor.getString(1));
		Log.d("debug", "Content is set to: "+ content.getContent());
		content.setDataType(cursor.getString(2));
		Log.d("debug", "Type is set to: "+ content.getDataType());
		content.setTime(new Time(Time.getCurrentTimezone()));
		content.setDescription(cursor.getString(4));
		Log.d("debug", "Description is set to: "+ content.getDescription());
		return content;
	}
	
}
