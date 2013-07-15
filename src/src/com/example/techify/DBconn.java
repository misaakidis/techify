package com.example.techify;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DBconn extends SQLiteOpenHelper {

	final static int DB_VERSION = 1;
	final static String DB_NAME = "techify.sqlite3";

	public DBconn(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	/*
	 * Called when the database is created for the first time.
	 * This is where the creation of tables and the initial population of 
	 * the tables should happen.(non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// Debug log that this is the first execution of the program and we create a new DB
		Log.d("DB", "Creating new Database");
		
		// Enable Foreign Keys constraints
		db.execSQL("PRAGMA foreign_keys=ON;");

		// SQL to initialize the DB schema
		String RSS_FEED_table = "CREATE TABLE RSS_FEED ("
				+ "_ID" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "TITLE" + " VARCHAR(127), "
				+ "LINK" + " VARCHAR(511) NOT NULL, "
				+ "DESCRIPTION" + " VARCHAR(511)"
				+ ");";
		String ARTICLES_table = "CREATE TABLE ARTICLES ("
				+ "_ID" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "RSS_FEED_ID" + " INT, "
				+ "TITLE" + " VARCHAR(127), "
				+ "PUBDATE" + " CHAR(8), "
				+ "DATA" + " BLOB, "
				+ "FOREIGN KEY (RSS_FEED_ID) REFERENCES RSS_FEED(_ID) ON DELETE CASCADE"
				+ ");";

		// Execute the SQL to create tables
		try{
			db.execSQL(RSS_FEED_table);
			db.execSQL(ARTICLES_table);
		} catch (SQLException e) {
			ActivityUtils.showMessageOK("SQLException on Database creation\n" + e.getMessage());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
