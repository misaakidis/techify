package com.example.techify;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class DBtools {
	
	// This class exposes methods for accessing and modifying the techify.sqlite3 database
	// Private instances
	private static DBconn dbconn;
	private static SQLiteDatabase db;

	public static void doConnect(Context context) {
		//Initialize the dbconn and db instances -- connect to the DB
		dbconn = new DBconn(context);
		try {
			db = dbconn.getWritableDatabase();
		} catch (Exception e){
			//TODO Handle "Could not connect to the database"
			ActivityUtils.showMessageOK("Could not connect to the database.\n" + e.getMessage());
		}
	}
	
	public static void doClose() {
		db.close();
	}

	// Insert one or multiple rows
	public static long insert(String table, String nullColumnHack, ContentValues values) {
		return db.insert(table, nullColumnHack, values);	
	}
	
	// Delete one or multiple rows
	public static int delete(String table, String whereClause, String[] whereArgs) {
		return db.delete(table, whereClause, whereArgs);
	}
	
	// Insert an article in the DB
	public static long insertArticle(Article article) {
		ContentValues values = new ContentValues();
		values.put("RSS_FEED_ID", article.getRss_feed_id());
		values.put("TITLE",article.getTitle());
		values.put("PUBDATE", article.getPubdateStr());
		values.put("DATA", article.getDocBytes());
		return db.insert("ARTICLES", null, values);
	}
	
	// Retrieve newest articles
	public static ArrayList<Article> getNewestArticles() {
	    ArrayList<Article> list = new ArrayList<Article>();
	    Cursor cursor = db.query("ARTICLES", null, null, null, null, null, "PUBDATE");
	    if (cursor.moveToFirst())
	    {
	    	Article article;
	        do
	        {
	        	article = new Article();
	        	article.set_id(cursor.getInt(0));
	        	article.setRss_feed_id(cursor.getInt(1));
	        	article.setTitle(cursor.getString(2));
	        	article.setPubdateStr(cursor.getString(3));
				article.setDocBytes(cursor.getBlob(4));
	            list.add(article);
	        }
	        while (cursor.moveToNext());
	    }
	    if (cursor != null && !cursor.isClosed()) 
	    {
	        cursor.close();
	    }

	    return list;
	}
}