package com.Cloud.Cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.Cloud.Cloud.services.EmailService;

@SpringBootApplication
public class CloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudApplication.class, args);
	}


	@Autowired
	private EmailService emailService;

     
	 void sendEmailTest(){
		emailService.sendEmail("goshopping854@gmail.com", "Just for testing email", "this is cloud project working email service");
	}

}
