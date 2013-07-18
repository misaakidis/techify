package com.example.techify;

import java.io.IOException;
import java.io.Serializable;

import org.jsoup.nodes.Document;

public class DocumentParcelable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -976971382424612633L;
	private Document doc;


	public DocumentParcelable(Document doc) {
		this.doc = doc;
	}

	
	/*
	public DocumentParcelable(Parcel in) {
		readFromParcel(in);
	}


	public DocumentParcelable(byte[] blob) {
		try {
			doc = ((DocumentParcelable) SerializationUtils.deserialize(blob)).getDoc();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public DocumentParcelable(byte[] blob) {
		Parcel parcel = Parcel.obtain();
		parcel.unmarshall(blob, 0, blob.length);
		readFromParcel(parcel);
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(doc);
	}

	private void readFromParcel(Parcel in) {
		// We just need to read back each
		// field in the order that it was
		// written to the parcel
		doc = (Document) in.readValue(DocumentParcelable.class.getClassLoader());
	}

	public static final Parcelable.Creator<DocumentParcelable> CREATOR = new Parcelable.Creator<DocumentParcelable>() {
		public DocumentParcelable createFromParcel(Parcel in) {
			return new DocumentParcelable(in);
		}

		public DocumentParcelable[] newArray(int size) {
			return new DocumentParcelable[size];
		}
	};
	 */


	public Document getDoc() {
		return doc;
	}


	public byte[] getBytes() {
		try {
			return SerializationUtils.serialize(new DocumentParcelable(doc));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	public byte[] getBytes() {
		Parcel parcel = Parcel.obtain();
		parcel.writeValue(doc);
		return parcel.marshall();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	 */

}
