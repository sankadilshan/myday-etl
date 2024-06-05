package com.sankadilshan.mydayuseretl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class MydayuseretlApplication {

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(MydayuseretlApplication.class, args)));
		System.out.println("### MyDay ETL Application started on port 9090 ###");
	}

}
