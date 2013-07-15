package com.example.techify;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class DBtools {
	
	// This class exposes methods for accessing and modifying the techify.sqlite3 database
	// Private instances
	private static DBconn dbconn;
	private static SQLiteDatabase db;

	public static void doConnect(Context context)
	{
		//Initialize the dbconn and db instances -- connect to the DB
		dbconn = new DBconn(context);
		try {
			db = dbconn.getWritableDatabase();
		} catch (Exception e){
			//TODO Handle "Could not connect to the database"
			ActivityUtils.showMessageOK("Could not connect to the database.\n" + e.getMessage());
		}
	}

	// Insert one or multiple rows
	public static long insert(String table, String nullColumnHack, ContentValues values)
	{
		return db.insert(table, nullColumnHack, values);	
	}
	
	// Delete one or multiple rows
	public static int delete(String table, String whereClause, String[] whereArgs)
	{
		return db.delete(table, whereClause, whereArgs);
	}
	
	// Insert an article in the DB
	public static long insertArticle(Article article)
	{
		ContentValues values = new ContentValues();
		values.put("_ID", article.get_id());
		values.put("RSS_FEED_ID", article.getRss_feed_id());
		values.put("TITLE",article.getTitle());
		values.put("PUBDATE", article.getPubdateStr());
		values.put("DATA", article.getData());
		return db.insert("ARTICLES", null, values);
	}
}