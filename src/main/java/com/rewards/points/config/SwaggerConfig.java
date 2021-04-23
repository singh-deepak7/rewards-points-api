package com.rewards.points.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Value("${swagger.description}")
	private String apiDescription;

	@Bean
	public OpenAPI rewardsPointsOpenAPI() {
		return new OpenAPI().info(apiInfo());
	}

	private Info apiInfo() {
		return new Info().title("rewards-points-api").version("1.0.0").description(apiDescription).contact(contacts());
	}

	private Contact contacts() {
		return new Contact().name("Deepak Singh").email("deepaksingh7@gmail.com");
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer yamlSwaggerDescription() {
		final PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		final YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		yaml.setResources(new ClassPathResource("swagger-resources/description.yml"));
		final Properties properties = yaml.getObject();
		if (null != properties) {
			propertySourcesPlaceholderConfigurer.setProperties(properties);
		}
		return propertySourcesPlaceholderConfigurer;
	}

}
