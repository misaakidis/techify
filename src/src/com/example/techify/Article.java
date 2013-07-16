package com.example.techify;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Article {

	// Articles POJO, with setters and getters

	private int _id;
	private int rss_feed_id;
	private String title;
	private Date pubdate;
	private byte[] data;

	public int get_id()
	{
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getRss_feed_id() {
		return rss_feed_id;
	}

	public void setRss_feed_id(int rss_feed_id) {
		this.rss_feed_id = rss_feed_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPubdate() {
		return this.pubdate;
	}

	public String getPubdateStr() {
		return new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(pubdate);
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	public byte[] getData() {
		return this.data.clone();
	}

	public void setData(byte[] data) {
		this.data = data.clone();
	}


	/*
	 * Debug Code to insert test article in DB
	 * 
	Article test = new Article();
	test.setRss_feed_id(1);
	test.setPubdate(new Date());
	test.setTitle("Test Article");
	try {
		test.setData("This is a simple article".getBytes("UTF-8"));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	DBtools.insertArticle(test);
	 */

}
