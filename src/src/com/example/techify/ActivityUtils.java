package com.example.techify;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
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
	public static void initialize(Activity MainActivity) {
		ActivityUtils.activity = MainActivity;
		ActivityUtils.context = MainActivity.getApplicationContext();		
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

	// Show an alert dialog with the message given
	public static void showMessageOKFinish(String message) {
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
		alert.setOnDismissListener(new OnDismissListener()
		{
			public void onDismiss(DialogInterface dialog)
			{
				activity.finish();
			}
		});
		alert.show();
	}

	// Show a toast message for a short time
	public static void showToastShort(String message) {
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.show();
	}

	// Show a toast message for a long time
	public static void showToastLong(String message) {
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
		toast.show();
	}

	// Show a progress dialog, use hideProgressDialog to dismiss it
	public static void showProgressDialog(String message) {
		progDialog = ProgressDialog.show(activity, "", message);
		progDialog.setCancelable(false);
	}

	// Show a progress dialog, use hideProgressDialog to dismiss it
	public static void showCancelableProgressDialog(String message, OnCancelListener onCancelListener) {
		progDialog = ProgressDialog.show(activity, "", message);
		progDialog.setCancelable(true);
		progDialog.setOnCancelListener(onCancelListener);
	}

	public static void hideProgressDialog() {
		if(progDialog != null)
			progDialog.dismiss();
		progDialog = null;
	}

	public static void hideAlert() {
		if(alert != null)
			alert.dismiss();
		alert = null;
	}

	public static void hideEverything() {
		hideProgressDialog();

	}


	// Checks if the device is connected to the Internet
	public static boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}

	// Asynchronously download a web page
	public static Document asyncGetWebPageDoc(String url) throws InterruptedException, ExecutionException {
		AsyncGetArticle getWebPage = new AsyncGetArticle(url);
		return getWebPage.execute().get();
	}

	// Asynchronously download a web page
	public static byte[] asyncGetWebPageBytes(String url) throws InterruptedException, ExecutionException {
		return new DocumentParcelable(asyncGetWebPageDoc(url)).getBytes();
	}

	// Inner class extending AsyncTask for requesting web pages and showing dialogs on a different thread
	private static class AsyncGetArticle extends AsyncTask<Void, Void, Document> {
		private String url;

		public AsyncGetArticle(String url) {
			this.url = url;
		}

		@Override
		protected void onPreExecute() {
			new OnCancelListener(){
				@Override
				public void onCancel(DialogInterface arg0) 
				{
					cancel(false);
					onPostExecute(null);
				}
			};
			ActivityUtils.showCancelableProgressDialog("Downloading requested article", null);
		}

		@Override
		protected Document doInBackground(Void... params) {
			Document doc = null;
			try {
				doc = Jsoup.connect(url).maxBodySize(0).get();
			} catch (Exception e) {
				cancel(false);
				onPostExecute(null);
			}
			return doc;
		}

		@Override
		protected void onPostExecute(Document doc) {
			super.onPostExecute(doc);
			ActivityUtils.hideProgressDialog();
		}

	}

	public static ArrayList<Article> getNewestArticlesDialog() {
		AsyncGetDownloadedArticles getDownloadedArticles = new AsyncGetDownloadedArticles();
		try {
			return getDownloadedArticles.execute().get();
		} catch (Exception e) {
			return null;
		}
	}

	// Inner class extending AsyncTask for retrieving the downloaded articles from DB
	private static class AsyncGetDownloadedArticles extends AsyncTask<Void, Void, ArrayList<Article>>{

		@Override
		protected void onPreExecute() {
			new OnCancelListener(){
				@Override
				public void onCancel(DialogInterface arg0) 
				{
					cancel(false);
					onPostExecute(null);
				}
			};
			ActivityUtils.showCancelableProgressDialog("Retrieving Downloaded Articles...", null);
		}

		@Override
		protected ArrayList<Article> doInBackground(Void... params) {
			ArrayList<Article> articleslist = DBtools.getNewestArticles();
			return articleslist;
		}

		@Override
		protected void onPostExecute(ArrayList<Article> articleslist) {
			super.onPostExecute(articleslist);
			ActivityUtils.hideProgressDialog();
		}

	}

}