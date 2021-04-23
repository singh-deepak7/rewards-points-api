package com.rewards.points.model;

import java.io.Serializable;

public class Points implements Serializable {

	private static final long serialVersionUID = -4315708482446019232L;
	private Integer points;

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Points [points=" + points + "]";
	}

}
