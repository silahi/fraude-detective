package com.comonitech.fraudedetective;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FraudedetectiveApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FraudedetectiveApplication.class, args);
	}

	@Autowired
	FakeDataGenerator dataGenerator;

	@Override
	public void run(String... args) throws Exception { 
		dataGenerator.generateData(); 
	}

}
