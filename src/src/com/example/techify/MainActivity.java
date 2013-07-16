/*	
	techify is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
	public void onResume() {
		super.onResume();
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
