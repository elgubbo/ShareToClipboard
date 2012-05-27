package com.elgubbo.sharetoclipboard;

import android.text.format.Time;

public class ShareContent {
	
	//content can eventually be changed to an abstract content class if media should be supported
	private String content;
	private long id;
	private String dataType;
	private Time time;
	
	public String getContent() {
		return content;
	}
	public long getId() {
		return id;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String toString(){
		return content;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataType() {
		return dataType;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public Time getTime() {
		return time;
	}

}
