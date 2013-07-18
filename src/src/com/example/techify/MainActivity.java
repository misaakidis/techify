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

import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;
import com.example.techify.ActivityUtils;

public class MainActivity extends Activity {

	public ActivityUtils activityUtils;
	private ListView listview;
	private ArrayAdapter<Article> articlesadapter;
	private ArrayList<Article> articleslist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onStart() {
		super.onStart();
		// Connect to the database
		DBtools.doConnect(this.getApplicationContext());
	}

	@Override
	public void onResume() {
		super.onResume();
		// Initialize utility class with methods for interacting with GUI
		ActivityUtils.initialize(this);
		//Intent i = new Intent(this, ReadArticleOnline.class);
		//startActivity(i);
		articleslist = null;
		showArticles();
	}

	private void showArticles() {
		/*
		 * Debug Code to insert test article in DB
		 * 
		 */
		Article test = new Article();
		test.setRss_feed_id(1);
		test.setPubdate(new Date());
		test.setTitle("Downloads");
		try {
			test.setDoc(ActivityUtils.asyncGetWebPageDoc("http://www.reuters.com/article/2013/07/17/net-us-nec-smartphones-idUSBRE96F11K20130717?feedType=RSS&feedName=technologyNews"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBtools.insertArticle(test);
		articleslist = ActivityUtils.getNewestArticlesDialog();
		if(articleslist == null || articleslist.isEmpty()) {
			ActivityUtils.showMessageOK("There are no articles");
			return;
		}
		listview = (ListView) findViewById(R.id.listViewArticles);
		articlesadapter = new ArrayAdapter<Article>(this, android.R.layout.simple_list_item_1, articleslist);
		listview.setAdapter(articlesadapter);
	}

	@Override
	public void onStop() {
		super.onStop();
		DBtools.doClose();
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
