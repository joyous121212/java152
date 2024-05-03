package com.itwill.project.model;

public class RentalInfo {
	
	public static final class Entity {
		public static final String TBL_RENTAL_INFO = "RENTAL_INFO";
		
		public static final String COL_DATE = "DD";
		public static final String COL_TIME = "TT";
		public static final String COL_ID_INFO = "ID";
	}
	
	private String date;
	private String time;
	private int id;
	
	public RentalInfo() {}
	
	public RentalInfo(String date, String time, int id) {
		this.date = date;
		this.time = time;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "RentalInfo [date=" + date + ", time=" + time + ", id=" + id + "]";
	}


	
}
