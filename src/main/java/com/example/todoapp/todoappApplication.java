package com.example.todoapp;

import java.util.Arrays;

import com.example.todoapp.entity.userEntity;
import com.example.todoapp.repository.userRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class todoappApplication {

	private static final Logger log = LoggerFactory.getLogger(todoappApplication.class);

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(todoappApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoSpring(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			// print all BeanDefinitionNames in commandline
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}

	@Bean
	public CommandLineRunner demoRepository(userRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new userEntity("Jack", "Bauer", "JackBauer", "bauer1"));
			repository.save(new userEntity("Chloe", "O'Brian", "ChloeObrian", "obrian1"));
			repository.save(new userEntity("Kim", "Bauer", "KimBauer", "bauer1"));
			repository.save(new userEntity("David", "Palmer", "DavidPalmer", "palmer1"));
			repository.save(new userEntity("Michelle", "Dessler", "MichelleDessler", "dessler1"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (userEntity customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			/*
			userEntity customer = repository.findById(51L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");
			*/

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			//  log.info(bauer.toString());
			// }
			log.info("");

		};
	}
}