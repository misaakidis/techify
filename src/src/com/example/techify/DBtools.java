package com.example.techify;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class DBtools{
	private static DBconn dbconn;
	private static SQLiteDatabase db;

	public static void doConnect(Context context)
	{
		dbconn = new DBconn(context);
		try {
			db = dbconn.getWritableDatabase();
		} catch (Exception e){
			//TODO Handle "Could not connect to the database"
			ActivityUtils.showMessageOK("Could not connect to the database.\n" + e.getMessage());
		}
	}

	public static long insert(String table, String nullColumnHack, ContentValues values)
	{
		return 0;	
	}
}