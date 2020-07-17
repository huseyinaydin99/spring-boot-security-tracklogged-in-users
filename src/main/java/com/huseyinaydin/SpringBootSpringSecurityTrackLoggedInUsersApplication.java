package com.huseyinaydin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.session.SessionRegistry;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@ComponentScan(basePackages = "com.huseyinaydin")
public class SpringBootSpringSecurityTrackLoggedInUsersApplication {
	public static String IMAGE_DIR;
	public static void main(String[] args) {
		IMAGE_DIR = "/opt/images/";
		SpringApplication.run(SpringBootSpringSecurityTrackLoggedInUsersApplication.class, args);
	}

}
