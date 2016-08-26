package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DavidApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DavidApiApplication.class, args);
	}

//	@Autowired UserRepository userRepository;
//	@Bean
//	public CommandLineRunner runner() throws Exception{
//		return (a) -> {
//			User user = new User();
//			user.setUsername("david");
//			user.setPassword("4860ss");
//			user.setUpdate(new Date());
//			user.setCreate(new Date());
//			user.setReg_id("dddsseeessdfdcsdfasds");
//			userRepository.save(user);
//
//		};
//	}


}

