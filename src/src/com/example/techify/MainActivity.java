package com.example.techify;

import android.os.Bundle;
import android.app.Activity;
import com.example.techify.ActivityUtils;

public class MainActivity extends Activity {

	public ActivityUtils activityUtils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onStart() {
		super.onStart();
		//Initialize utility class with methods for interacting with GUI
		ActivityUtils.initialize(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(!ActivityUtils.isNetworkAvailable())
			ActivityUtils.showMessageOK("No Internet Connection");

	}

	/*
	 * The app will not be using the Menu button (which is now considered obsolete).
	 * 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 */

}
