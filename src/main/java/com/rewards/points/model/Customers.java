package com.rewards.points.model;

import java.io.Serializable;
import java.util.List;

public class Customers  implements Serializable {
	
	private static final long serialVersionUID = -7104549706167412842L;
	
	private List<Customer> customers;

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Customers [customers=" + customers + "]";
	}
	

}
