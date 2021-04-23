package com.rewards.points.model;

import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable {

	private static final long serialVersionUID = -241109675701749526L;

	private int id;
	private List<Rewards> rewards; 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public List<Rewards> getRewards() {
		return rewards;
	}

	public void setRewards(List<Rewards> rewards) {
		this.rewards = rewards;
	}

	public Customer(int id,  List<Rewards> rewards) {
		this.id = id;
		this.rewards = rewards;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", rewards=" + rewards + "]";
	}


}
