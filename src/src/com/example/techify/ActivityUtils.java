package com.example.techify;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ActivityUtils {

	private static Activity activity;
	private static Context context;
	public static AlertDialog alert;

	//Call this method after changing activity on onResume
	public static void initialize(Activity MainActivity)
	{
		ActivityUtils.activity = MainActivity;
		ActivityUtils.context = MainActivity.getApplicationContext();
		DBtools.doConnect(ActivityUtils.context);
	}
	
	//Show an alert dialog with the message given
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
	
	//Checks if the device is connected to the Internet
	public static boolean isNetworkAvailable() 
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}
}