package com.example.techify;

import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
	
	// Articles POJO, with setters and getters
	
	private int _id;
	private int rss_feed_id;
	private String title;
	private Date pubdate;
	private Blob data;
	
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
		return new SimpleDateFormat("yyyyMMMMddddHHmmss").format(pubdate);
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	public byte[] getData() {
		try {
			return data.getBytes(1, (int) data.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setData(Blob data) {
		try {
			this.data.setBytes(1, data.getBytes(1, (int) data.length()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
