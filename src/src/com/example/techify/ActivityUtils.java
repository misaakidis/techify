package com.example.techify;

import java.net.URL;

import org.jsoup.nodes.Document;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

public class ActivityUtils {

	// Utilities for interacting with the UI and the device

	private static Activity activity;
	private static Context context;
	public static AlertDialog alert;
	private static ProgressDialog progDialog;

	// Call this method after changing activity on onResume
	public static void initialize(Activity MainActivity)
	{
		ActivityUtils.activity = MainActivity;
		ActivityUtils.context = MainActivity.getApplicationContext();
		//context.deleteDatabase("techify.sqlite3");
		DBtools.doConnect(ActivityUtils.context);
	}

	// Show an alert dialog with the message given
	public static void showMessageOK(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(message)
		.setCancelable(false)
		.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) 
			{
				alert.cancel();
			}
		});
		alert = builder.create();
		alert.show();
	}

	// Show a toast message for a short time
	public static void showToastShort(String message)
	{
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.show();
	}

	// Show a toast message for a long time
	public static void showToastLong(String message)
	{
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
		toast.show();
	}

	// Show a progress dialog, use hideProgressDialog to dismiss it
	public static void showProgressDialog(String message)
	{
		progDialog = ProgressDialog.show(activity, "", message);
		progDialog.setCancelable(false);
	}

	// Show a progress dialog, use hideProgressDialog to dismiss it
	public static void showCancelableProgressDialog(String message, OnCancelListener onCancelListener)
	{
		progDialog = ProgressDialog.show(activity, "", message);
		progDialog.setCancelable(true);
		progDialog.setOnCancelListener(onCancelListener);
	}

	public static void hideProgressDialog()
	{
		if(progDialog != null)
			progDialog.dismiss();
		progDialog = null;
	}

	public static void hideAlert()
	{
		if(alert != null)
			alert.dismiss();
		alert = null;
	}

	public static void hideEverything()
	{
		hideProgressDialog();

	}


	// Checks if the device is connected to the Internet
	public static boolean isNetworkAvailable() 
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}

	// Download a web page
	public static Document getWebPage(URL url) {
		Document doc;
		AsyncGetWebPage getWebPage = new AsyncGetWebPage(url);
		getWebPage.execute();
		return null;
	}

	// Inner class extending AsyncTask for requesting web pages and showing dialogs on a different thread
	private static class AsyncGetWebPage extends AsyncTask<Void, Void, Document>
	{
		private URL url;
		
		public AsyncGetWebPage(URL url) {
			this.url = url;
		}

		@Override
		protected void onPreExecute()
		{
			ActivityUtils.showProgressDialog("Downloading requested article");
		}
		
		@Override
		protected Document doInBackground(Void... params) {
			return null;
		}

		@Override
		protected void onPostExecute(Document doc)
		{
			super.onPostExecute(doc);
			ActivityUtils.hideProgressDialog();
		}

	}
}