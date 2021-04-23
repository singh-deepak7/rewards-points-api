package com.rewards.points.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;


@Hidden
@RestController
public class RedirectController {

	private String swaggerURL = "swagger-ui.html";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void swaggerRedirect(HttpServletResponse response) throws IOException {
		response.sendRedirect(swaggerURL);
	}

}
