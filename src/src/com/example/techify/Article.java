package com.example.techify;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.jsoup.nodes.Document;


public class Article {

	// Articles POJO, with setters and getters

	private int _id;
	private int rss_feed_id;
	private String title;
	private Date pubdate;
	private Document doc;

	public int get_id() {
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

	public void setPubdateStr(String pubdate) {
		try {
			this.pubdate = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).parse(pubdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	@Override
	public String toString() {
		return getTitle();
	}

	public byte[] getDocBytes() {
		return new DocumentParcelable(doc).getBytes();
	}

	public void setDocBytes(byte[] blob) {
		//doc = new DocumentParcelable(blob).getDoc();
	}

}
