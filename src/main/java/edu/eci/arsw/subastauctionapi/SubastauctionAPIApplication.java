package edu.eci.arsw.subastauctionapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.subastauction"})
@EnableMongoRepositories("edu.eci.arsw.subastauction.persistence")
public class SubastauctionAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubastauctionAPIApplication.class, args);
	}
}
