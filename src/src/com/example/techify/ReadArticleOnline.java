package com.example.techify;

import org.jsoup.nodes.Document;

import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.support.v4.app.NavUtils;

public class ReadArticleOnline extends Activity {
	
	Document doc = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_article_online);
		// Show the Up button in the action bar.
		setupActionBar();
		
		ActivityUtils.initialize(this);

		try {
			doc = ActivityUtils.asyncGetWebPageDoc("http://fulltextrssfeed.com/feeds.reuters.com/reuters/technologyNews");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_article, menu);
		return true;
	}
	 */

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
		ActivityUtils.initialize(this);
		if(doc == null)
		{
			ActivityUtils.showMessageOKFinish("Cannot load the link requested.");
			return;
		}
		WebView webViewArticle = (WebView)findViewById(R.id.webViewArticle);
		webViewArticle.setFocusableInTouchMode(false);
		webViewArticle.setFocusable(false);
		webViewArticle.getSettings().setBuiltInZoomControls(true);
		webViewArticle.getSettings().setDisplayZoomControls(true);
		webViewArticle.loadData(doc.html(), "text/html", "null");
	}
}
