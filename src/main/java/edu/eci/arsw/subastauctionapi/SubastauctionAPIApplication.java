package edu.eci.arsw.subastauctionapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.subastauction"})
public class SubastauctionAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubastauctionAPIApplication.class, args);
	}
}
