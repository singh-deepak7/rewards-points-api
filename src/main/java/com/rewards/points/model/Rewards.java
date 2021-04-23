package com.rewards.points.model;

import java.io.Serializable;
import java.util.Date;

public class Rewards implements Serializable , Comparable<Rewards>{

	private static final long serialVersionUID = -3156961341765219543L;

	private String payer;
	private Integer points;
	private Date timestamp;

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Rewards(String payer, Integer points, Date timestamp) {
		this.payer = payer;
		this.points = points;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Rewards [payer=" + payer + ", points=" + points + ", timestamp=" + timestamp + "]";
	}
	
	@Override
	public int compareTo(Rewards r) {
		return getTimestamp().compareTo(r.getTimestamp());
	}

}
