package com.rewards.points.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.rewards.points.exception.CustomerNotFoundException;
import com.rewards.points.exception.InvalidInputException;
import com.rewards.points.model.Customer;
import com.rewards.points.model.Customers;
import com.rewards.points.model.Points;
import com.rewards.points.model.Rewards;

@Component
public class RewardsPointsDAO {

	private static Customers customerList = new Customers();
	private static List<Customer> customer = new ArrayList<Customer>();
	private static List<Rewards> rewards = new ArrayList<Rewards>();
	private static Map<String, Integer> pointsMap = new HashMap<>();

	private static final Logger LOGGER = LoggerFactory.getLogger(RewardsPointsDAO.class);

	static {
		rewards.add(new Rewards("Amazon", 2000, new Date()));
		rewards.add(new Rewards("eBay", 200, new Date()));
		Customer customerOne = new Customer(1, rewards);
		customer.add(customerOne);
	}

	public Customers getAllCustomers() {
		customerList.setCustomers(customer);
		return customerList;
	}

	public JSONObject getBalance(int id) {

		if (isUserPresent(id)) {
			JSONObject balanceObject = new JSONObject();
			Map<String, Integer> balanceMap = new HashMap<>();
			Multimap<String, Integer> map = ArrayListMultimap.create();
			for (Customer custList : customerList.getCustomers()) {

				if (custList.getId() == id) {
					for (Rewards customerReward : custList.getRewards()) {
						map.put(customerReward.getPayer(), customerReward.getPoints());
					}
				}
			}

			for (String key : map.keySet()) {
				Map<String, Integer> m = Maps.transformValues(map.asMap(),
						ints -> ints.stream().mapToInt(Integer::intValue).sum());
				balanceMap.put(key, m.get(key));
			}

			if (null != pointsMap && !pointsMap.isEmpty()) {
				for (Map.Entry<String, Integer> entry : pointsMap.entrySet()) {
					for (Map.Entry<String, Integer> e : balanceMap.entrySet()) {
						if (entry.getKey().equals(e.getKey())) {
							balanceObject.put(entry.getKey(), e.getValue() - entry.getValue());
						}
					}

				}
			} else {
				for (Map.Entry<String, Integer> e : balanceMap.entrySet()) {
					balanceObject.put(e.getKey(), e.getValue());
				}
			}

			return balanceObject;
		} else {
			throw new CustomerNotFoundException();
		}
	}

	public void addBalance(int id, Rewards reward) {
		boolean existingId = false;
		for (Customer custList : customerList.getCustomers()) {
			if (custList.getId() == id) {
				existingId = true;
				checkNegativeBalance(id, reward.getPayer(), reward.getPoints());
				custList.getRewards().add(new Rewards(reward.getPayer(), reward.getPoints(), reward.getTimestamp()));
			}

		}
		if (!existingId) {
			List<Rewards> point = new ArrayList<Rewards>();
			point.add(new Rewards(reward.getPayer(), reward.getPoints(), reward.getTimestamp()));
			Customer newCust = new Customer(id, point);
			customer.add(newCust);
		}
	}

	private void checkNegativeBalance(int id, String payer, Integer currentPoints) {
		if (currentPoints < 0) {
			if ((Integer) getBalance(id).get(payer) + currentPoints < 0) {
				LOGGER.info("Balance cannot be negative");
				throw new InvalidInputException("Balance cannot be negative!");
			}
		}

	}

	public JSONObject spendBalance(int id, Points spendingPoints) {

		if (isUserPresent(id)) {
			JSONObject spendBalanceObject = new JSONObject();
			int totalPoints = spendingPoints.getPoints();
			for (Customer custList : customerList.getCustomers()) {
				if (custList.getId() == id) {
					List<Rewards> rewardsList = custList.getRewards();

					Collections.sort(rewardsList);

					while (totalPoints > 0) {
						for (Rewards data : rewardsList) {
							if (data.getPoints() > 0) {
								if (totalPoints - data.getPoints() >= 0) {
									pointsMap.put(data.getPayer(), data.getPoints());
									totalPoints = totalPoints - data.getPoints();
								}

								if (totalPoints - data.getPoints() < 0) {
									pointsMap.put(data.getPayer(), totalPoints);
									totalPoints = totalPoints - data.getPoints();
								}
							}
							if (data.getPoints() < 0 && pointsMap.containsKey(data.getPayer())) {
								pointsMap.put(data.getPayer(), pointsMap.get(data.getPayer()) + data.getPoints());
								totalPoints = totalPoints + -data.getPoints();
							}
							if (totalPoints < 0)
								break;
						}
					}

				}
			}

			for (Map.Entry<String, Integer> entry : pointsMap.entrySet()) {
				spendBalanceObject.put(entry.getKey(), (~(entry.getValue() - 1)));
			}

			return spendBalanceObject;
		} else {
			throw new CustomerNotFoundException();
		}

	}

	private boolean isUserPresent(int id) {
		List<Integer> idList = new ArrayList<Integer>(customerList.getCustomers().size());
		for (Customer custList : customerList.getCustomers()) {
			idList.add(custList.getId());
		}

		return idList.contains(id);

	}

}
