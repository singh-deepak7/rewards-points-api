package com.rewards.points.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.points.dao.RewardsPointsDAO;
import com.rewards.points.model.Customers;
import com.rewards.points.model.ErrorResponse;
import com.rewards.points.model.Points;
import com.rewards.points.model.Rewards;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/v1")
@Tag(description = "Controller listing the resource supported by the API" , name  = "RewardsPointControler")
public class RewardsPointsController {

	@Autowired
	private RewardsPointsDAO rewardsDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RewardsPointsController.class);
	
	@Operation(description = "Get all customer points")
	@ApiResponses(value = {
	@ApiResponse( responseCode = "200" , description = "List of Customers points" , content = @Content(schema  = @Schema(implementation = Customers.class))),
	@ApiResponse( responseCode = "400" , description = "Bad request" , content = @Content(schema  = @Schema(implementation = ErrorResponse.class))),
	@ApiResponse( responseCode = "404" , description = "No data found" , content = @Content(schema  = @Schema(implementation = ErrorResponse.class))),
	@ApiResponse( responseCode = "500" , description = "Internal Server error" , content = @Content(schema  = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping(path = "/all", produces = "application/json")
	public Customers getCustomers() {
		LOGGER.info("Request recieved for getAllCustomers");
		return rewardsDao.getAllCustomers();
	}
	
	
	@Operation(description = "Get Points for specific customer")
	@ApiResponses(value = {
	@ApiResponse( responseCode = "200" , description = "List of Customers points" , content = @Content(schema  = @Schema(implementation = String.class))),
	@ApiResponse( responseCode = "400" , description = "Bad request" , content = @Content(schema  = @Schema(implementation = ErrorResponse.class))),
	@ApiResponse( responseCode = "404" , description = "No data found" , content = @Content(schema  = @Schema(implementation = ErrorResponse.class))),
	@ApiResponse( responseCode = "500" , description = "Internal Server error" , content = @Content(schema  = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping(path="/balance/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String>getBalance(@PathVariable int id) 
	{
		LOGGER.info("Request recieved for checking balance for id="+id);
		String rewardsBalance =  rewardsDao.getBalance(id).toString();
		if (null == rewardsBalance) {
	        return ResponseEntity.notFound().build();
	    } else {
	        return ResponseEntity.ok(rewardsBalance);
	    }
	}
	
	@Operation(description = "Add ponts for a specific customer")
	@ApiResponses(value = {
	@ApiResponse( responseCode = "200" , description = "Add Customers points" , content = @Content(schema  = @Schema(implementation = String.class))),
	@ApiResponse( responseCode = "400" , description = "Bad request" , content = @Content(schema  = @Schema(implementation = ErrorResponse.class))),
	@ApiResponse( responseCode = "404" , description = "No data found" , content = @Content(schema  = @Schema(implementation = ErrorResponse.class))),
	@ApiResponse( responseCode = "500" , description = "Internal Server error" , content = @Content(schema  = @Schema(implementation = ErrorResponse.class)))
	})
	@PostMapping(path="/balance/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String>addBalance(@PathVariable int id,@RequestBody Rewards rewards) 
	{
		LOGGER.info("Request recieved to add balance for id="+ id  + " with balance="+ rewards.toString());
		rewardsDao.addBalance(id,rewards);
		return new ResponseEntity<>("Rewards added", HttpStatus.CREATED);
	}
	
	
	@Operation(description = "Spend Points for a specific customer")
	@ApiResponses(value = {
	@ApiResponse( responseCode = "200" , description = "Spend Customers points" , content = @Content(schema  = @Schema(implementation = String.class))),
	@ApiResponse( responseCode = "400" , description = "Bad request" , content = @Content(schema  = @Schema(implementation = ErrorResponse.class))),
	@ApiResponse( responseCode = "404" , description = "No data found" , content = @Content(schema  = @Schema(implementation = ErrorResponse.class))),
	@ApiResponse( responseCode = "500" , description = "Internal Server error" , content = @Content(schema  = @Schema(implementation = ErrorResponse.class)))
	})
	@DeleteMapping(path="/spend/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String>spendBalance(@PathVariable int id,@RequestBody Points points) 
	{
		LOGGER.info(" Request recieved to spend balance for id="+ id  + " with balance "+ points.toString() );
		String balance = rewardsDao.spendBalance(id,points).toString();
		return new ResponseEntity<String>(balance, HttpStatus.OK);
	}

}
